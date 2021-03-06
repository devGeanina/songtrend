package com.soundtrap.songtrend.repository;
import org.springframework.data.repository.CrudRepository;

import com.soundtrap.songtrend.entity.Timeserie;
import com.soundtrap.songtrend.entity.Word;


public interface TimeserieRepository extends CrudRepository<Timeserie, Long>, CustomTimeseriesRepository<Word, Timeserie>{

}
