package com.soundtrap.songtrend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.soundtrap.songtrend.dto.WordDTO;
import com.soundtrap.songtrend.entity.Word;

@Component
public class DTOtoWordConverter implements Converter<WordDTO, Word>{

	@Override
	public Word convert(WordDTO dto) {
		Word word = new Word();
		if(dto.getId() != null && !StringUtils.isEmpty(dto.getId())) {
			word.setWordId(new Long(dto.getId()));
			
		}
		word.setWordName(dto.getWord());  
	    return word;
	}

}
