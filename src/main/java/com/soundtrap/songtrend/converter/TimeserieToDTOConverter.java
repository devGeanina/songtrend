package com.soundtrap.songtrend.converter;

import org.springframework.stereotype.Component;

import com.soundtrap.songtrend.dto.TimeserieDTO;
import com.soundtrap.songtrend.entity.Timeserie;
import com.soundtrap.songtrend.utils.Utils;

import org.springframework.core.convert.converter.Converter;

@Component
public class TimeserieToDTOConverter implements Converter<Timeserie, TimeserieDTO> {

	@Override
	public TimeserieDTO convert(Timeserie timeserie) {
		TimeserieDTO dto = new TimeserieDTO();
		dto.setCount(timeserie.getCount());
		dto.setId(timeserie.getTimeserieId());
		dto.setDate(Utils.getStringDate(timeserie.getCountDate()));
		return dto;
	}

}
