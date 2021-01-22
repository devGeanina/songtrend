package com.soundtrap.songtrend.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="words")
public class Word implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long wordId;
    
    @Column(name="name", nullable=false)
    private String wordName;
    
    @OneToMany(mappedBy="word",fetch=FetchType.LAZY)
	private List<Timeserie> timeseries;

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

}
