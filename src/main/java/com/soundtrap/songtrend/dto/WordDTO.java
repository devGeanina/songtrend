package com.soundtrap.songtrend.dto;
import java.util.List;

public class WordDTO{
	
	private Long id;
	private String word;
	private List<TimeserieDTO> timeseries;
	
	public WordDTO() {}
	
	public WordDTO(String word, List<TimeserieDTO> timeseries, int count) {
		this.word = word;
		this.timeseries = timeseries;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<TimeserieDTO> getTimeseries() {
		return timeseries;
	}
	public void setTimeseries(List<TimeserieDTO> timeseries) {
		this.timeseries = timeseries;
	}

	@Override
	public String toString() {
		return "WordDTO [id=" + id + ", word=" + word + ", timeseries=" + timeseries + "]";
	}
}
