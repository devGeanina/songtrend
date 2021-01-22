package com.soundtrap.songtrend.service;

import java.util.List;
import java.util.Map;

import com.soundtrap.songtrend.dto.WordDTO;

public interface WordService {
	 void insertWords(List<WordDTO> word);
	 WordDTO findWordByDateAndName(String name, Long dateStart, Long dateEnd);
	 List<WordDTO> getWordJSON(String url);
	 String fetchWord(Map<String, String> params);
	 void insertMockData();
}
