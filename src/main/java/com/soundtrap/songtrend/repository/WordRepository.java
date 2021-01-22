package com.soundtrap.songtrend.repository;
import org.springframework.data.repository.CrudRepository;

import com.soundtrap.songtrend.entity.Word;


public interface WordRepository extends CrudRepository<Word, Long>, CustomWordRepository<Word, String>{

}
