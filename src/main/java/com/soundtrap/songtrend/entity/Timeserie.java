package com.soundtrap.songtrend.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="timeseries")
public class Timeserie implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long timeserieId;
    
	@ManyToOne
	@JoinColumn(name="word_id")
	private Word word;
    
    @Column(name="count", nullable=false)
	private Integer count;
    
    @Column(name="count_date_ts", nullable=false)
	private Long countDate;

	public Long getTimeserieId() {
		return timeserieId;
	}

	public void setTimeserieId(Long timeserieId) {
		this.timeserieId = timeserieId;
	}

	public Long getCountDate() {
		return countDate;
	}

	public void setCountDate(Long countDate) {
		this.countDate = countDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}
}
