package com.soundtrap.songtrend.dto;

public class TimeserieDTO{

	private Long id;
	private String date;
	private int count;
	
	public TimeserieDTO() {}
	
	public TimeserieDTO(int count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "TimeserieDTO [id=" + id + ", date=" + date + ", count=" + count + "]";
	}
	
}
