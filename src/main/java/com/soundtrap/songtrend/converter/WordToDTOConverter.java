package com.soundtrap.songtrend.converter;

import org.springframework.stereotype.Component;

import com.soundtrap.songtrend.dto.WordDTO;
import com.soundtrap.songtrend.entity.Word;

import org.springframework.core.convert.converter.Converter;

@Component
public class WordToDTOConverter implements Converter<Word, WordDTO>{

	@Override
	public WordDTO convert(Word word) {
		WordDTO dto = new WordDTO();
		dto.setId(word.getWordId());
		dto.setWord(word.getWordName());
		return dto;
	}

}
