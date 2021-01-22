package com.soundtrap.songtrend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.soundtrap.songtrend.converter.DTOtoTimeserieConverter;
import com.soundtrap.songtrend.dto.TimeserieDTO;
import com.soundtrap.songtrend.dto.WordDTO;
import com.soundtrap.songtrend.entity.Timeserie;
import com.soundtrap.songtrend.entity.Word;
import com.soundtrap.songtrend.repository.TimeserieRepository;
import com.soundtrap.songtrend.repository.WordRepository;
import com.soundtrap.songtrend.utils.TrackJSON;
import com.soundtrap.songtrend.utils.Utils;

@Service
public class WordServiceImpl implements WordService{
	
	private static final Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
	private WordRepository wordRepository;
	private TimeserieRepository timeserieRepository;
	private DTOtoTimeserieConverter dtoToTimeserieConverter;
	
	@Autowired
	public WordServiceImpl(WordRepository wordRepository, TimeserieRepository timeserieRepository,
			DTOtoTimeserieConverter dtoToTimeserieConverter) {
		super();
		this.wordRepository = wordRepository;
		this.timeserieRepository = timeserieRepository;
		this.dtoToTimeserieConverter = dtoToTimeserieConverter;
	}

	@Override
	public void insertWords(List<WordDTO> words) {
		if(!words.isEmpty()) {
			
			for(WordDTO w : words) {
				Word word = new Word();
				word.setWordName(w.getWord().toLowerCase());
				Word existingWord = wordRepository.findWordByName(word.getWordName().toLowerCase().trim());
				Word wordToSave = new Word();
				
				if(existingWord == null) {
					wordToSave = wordRepository.save(word);
					logger.info("Saved word: " + wordToSave.getWordName() + ".");
				}else {
					wordToSave = existingWord;
				}
				
				for(TimeserieDTO timeserieDTO: w.getTimeseries()) {
					timeserieDTO.setDate(Utils.now());
					Timeserie timeserie = dtoToTimeserieConverter.convert(timeserieDTO);
					timeserie.setWord(wordToSave);
					timeserieRepository.save(timeserie);
				}
			}
		}
	}

	@Override
	public WordDTO findWordByDateAndName(String name, Long dateStart, Long dateEnd) {
		WordDTO wordDTO = new WordDTO();
		logger.info("The word that we are searching for is: " + name + ".");
		Long wordId = 0L;
		wordId = wordRepository.findWordIdByName(name.toLowerCase().trim());
		List<Timeserie> wordTimeseries = new ArrayList<Timeserie>();
		
		if(wordId != null && wordId != 0L) {
			wordTimeseries = timeserieRepository.findByWordIdAndDate(wordId, dateStart, dateEnd);
			logger.info("Word found!");
			wordDTO.setWord(name);
			List<TimeserieDTO> timeserieDTOs = new ArrayList<TimeserieDTO>();
			if(!wordTimeseries.isEmpty()) {
				for(Timeserie timeserie : wordTimeseries) {
					TimeserieDTO timeserieDTO = new TimeserieDTO();
					timeserieDTO.setCount(timeserie.getCount());
					timeserieDTO.setDate(Utils.getStringDate(timeserie.getCountDate()));
					timeserieDTOs.add(timeserieDTO);
				}
			}
			wordDTO.setTimeseries(timeserieDTOs);
		}else {
			logger.info("No data found for this word.");
		}
	
		return wordDTO;
	}
	
	public String getJSON(String requiredUrl) {
		String jsonResult = "";

		try {
	        URL url = new URL(requiredUrl); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
	        int response = connection.getResponseCode();
	        
	        switch (response) {
	        
	        case HttpURLConnection.HTTP_OK:
	            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String responseLine;
	            StringBuilder builder = new StringBuilder();
	
	            while ((responseLine = responseReader.readLine()) != null) {
	                builder.append(responseLine);
	            }
	
	            responseReader.close();
	            jsonResult = builder.toString();
	            return jsonResult;
	
	        case HttpURLConnection.HTTP_BAD_GATEWAY:
	        case HttpURLConnection.HTTP_UNAVAILABLE:
	        case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
	        case HttpURLConnection.HTTP_NOT_FOUND:
	        	logger.info("Server responded with the next status:" + connection.getResponseCode());
	            break;
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    return jsonResult;
	}
	
	public List<WordDTO> getWordJSON(String url) {
		 //fetch data
		 Gson gson = new Gson();
         Type listType = new TypeToken<ArrayList<TrackJSON>>(){}.getType();
         String jsonResult = getJSON(url);
         List<TrackJSON> tracks = gson.fromJson(jsonResult, listType); 
         List<WordDTO> fetchedWordDTOs = new ArrayList<WordDTO>();

         for(TrackJSON track: tracks) {
         	getWords(fetchedWordDTOs, track);
         }
         
         logger.info("Fetched words:" + fetchedWordDTOs.toString());
       
		 for(WordDTO w : fetchedWordDTOs) {
		    int count = 0;
		    
			for(TrackJSON t: tracks) {
				if(t.getTitle().contains(w.getWord())){
					count++;
				}
			}
			
			List<TimeserieDTO> timeserieDTOs = new ArrayList<TimeserieDTO>();
			timeserieDTOs.add(new TimeserieDTO(count));
			w.setTimeseries(timeserieDTOs);
         }
		 
         addWords(fetchedWordDTOs);
         return fetchedWordDTOs;
	}
	
	public void addWords(List<WordDTO> words) {
		if(words.isEmpty()) {
			logger.info("The list of words couldn't be fetched.");
		}else {
		    List<WordDTO> noDupeWordsList = new ArrayList<>(new HashSet<>(words));
			logger.info("Saving data without duplicates:" + noDupeWordsList.toString());
			this.insertWords(noDupeWordsList);
		}
	}
	
	private void getWords(List<WordDTO> fetchedWordDTOs, TrackJSON track) {
		WordDTO wordDTO = new WordDTO();
		String[] words = track.getTitle().split("\\s+");
		for (int i = 0; i < words.length; i++) {
		    words[i] = words[i].replaceAll("[^\\w]", "");
		}
		List<String> titleWords = new ArrayList<String>();
		Collections.addAll(titleWords, words);
		for(String titleWord: titleWords) {
			if(!titleWord.equals("") && !Utils.isNumeric(titleWord)) {
		     	wordDTO.setWord(titleWord);
		     	fetchedWordDTOs.add(wordDTO);
			}
		}
	}
	
	public String fetchWord(Map<String, String> params) {
		WordDTO foundWordDTO = new WordDTO();
		String searchWord = params.get("word");
		Long startDate = Utils.getDateInMillis(params.get("from"));
		Long endDate = Utils.getDateInMillis(params.get("to"));
		foundWordDTO = findWordByDateAndName(searchWord, startDate, endDate);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = "";
		if(foundWordDTO != null) {
			result = gson.toJson(foundWordDTO);
		}

		return result;
	}
	
	//inserting some mock data to match the given URL with the word love - http://localhost:8080/word/?word=love&from=20161231&to=20170101
	public void insertMockData() {
		Word word = new Word();
		word.setWordName("love");
		Word existingWord = wordRepository.findWordByName(word.getWordName().toLowerCase().trim());
		Word wordToSave = new Word();
		
		if(existingWord == null) {
			wordToSave = wordRepository.save(word);
		}else {
			wordToSave = existingWord;
		}

		Timeserie timeserie1 = new Timeserie();
		timeserie1.setCount(20);
		timeserie1.setCountDate(Utils.getDateInMillis("20161231"));
		timeserie1.setWord(wordToSave);
		timeserieRepository.save(timeserie1);
		Timeserie timeserie2 = new Timeserie();
		timeserie2.setCount(19);
		timeserie2.setCountDate(Utils.getDateInMillis("20170101"));
		timeserie2.setWord(wordToSave);
		timeserieRepository.save(timeserie2);
	}
}
