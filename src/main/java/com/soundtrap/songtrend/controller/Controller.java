package com.soundtrap.songtrend.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.soundtrap.songtrend.converter.DTOtoTimeserieConverter;
import com.soundtrap.songtrend.service.WordService;
import com.soundtrap.songtrend.utils.Utils;


@RestController
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	private WordService wordService;
	private DTOtoTimeserieConverter dtoToTimeserieConverter;
	
	@Autowired
	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	@Autowired
	public void setDtoToTimeserieConverter(DTOtoTimeserieConverter dtoToTimeserieConverter) {
		this.dtoToTimeserieConverter = dtoToTimeserieConverter;
	}

	@RequestMapping("/")
	public String getWords() {
		wordService.getWordJSON(Utils.SOUNDCLOUD_URL);
		return "Words have been fetched from the Soundcloud API successfully.";
	}
	
	@RequestMapping(value ="/word")
	public String getURLValue(HttpServletRequest request){
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
	    String queryString = request.getQueryString();
	    String urlString = "";

	    if (queryString == null) {
	    	urlString = requestURL.toString();
	    } else {
	    	urlString = requestURL.append('?').append(queryString).toString();
	    }
	    
	    return wordService.fetchWord(getUrlValues(urlString));
	}
	
	public Map<String, String> getUrlValues(String url) {
	    int i = url.indexOf("?");
	    Map<String, String> paramsMap = new HashMap<>();
	    
	    if (i > -1) {
	        String searchURL = url.substring(url.indexOf("?") + 1);
	        String params[] = searchURL.split("&");

	        for (String param : params) {
	            String temp[] = param.split("=");
	            try {
					paramsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					logger.info("The URL format is not accepted. Please revise it.");
				}
	        }
	    }
	    
	    logger.info(paramsMap.toString());

	    return paramsMap;
	}
}
