package com.soundtrap.songtrend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soundtrap.songtrend.entity.Word;

public interface CustomWordRepository<T, S> {
	@Query("SELECT w FROM words w WHERE w.wordName = :name")
	Word findWordByName(@Param("name")String name);
	
	@Query("SELECT w.id FROM words w WHERE w.wordName = :name")
	Long findWordIdByName(@Param("name")String name);
}
