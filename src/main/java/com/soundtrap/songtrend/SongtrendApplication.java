package com.soundtrap.songtrend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.soundtrap.songtrend.service.WordService;
import com.soundtrap.songtrend.utils.Utils;

@SpringBootApplication
@EnableScheduling
public class SongtrendApplication {
	
	@Autowired
	private WordService wordService;
	
    public static void main( String[] args )
    {
    	SpringApplication.run(SongtrendApplication.class, args);
    }
    
    @Scheduled(fixedDelay = Utils.FETCH_PERIOD)
    public void run() {
    	//always waits for the last task to be finished
    	System.out.println("Fetching daily data.");
    	wordService.getWordJSON(Utils.SOUNDCLOUD_URL);
    }
    
    @PostConstruct
    private void init() {
        wordService.insertMockData();
    }
}
