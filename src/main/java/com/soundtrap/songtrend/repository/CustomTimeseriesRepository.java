package com.soundtrap.songtrend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soundtrap.songtrend.entity.Timeserie;

public interface CustomTimeseriesRepository <T, S> {
	@Query("SELECT t FROM timeseries t WHERE t.word.id = :wordId AND t.countDate BETWEEN :startDate AND :endDate")
	List<Timeserie> findByWordIdAndDate(@Param("wordId")Long wordId, @Param("startDate")Long startDate, @Param("endDate")Long endDate);
}
