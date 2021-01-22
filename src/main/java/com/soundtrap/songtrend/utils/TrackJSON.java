package com.soundtrap.songtrend.utils;

public class TrackJSON {

	private Long id;
	private String created_at;
	private String title;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreatedAt() {
		return created_at;
	}
	
	public void setCreatedAt(String createdAt) {
		this.created_at = createdAt;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "TrackJSON [id=" + id + ", createdAt=" + created_at + ", title=" + title + "]";
	}
}
