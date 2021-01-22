package com.soundtrap.songtrend.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.soundtrap.songtrend.dto.TimeserieDTO;
import com.soundtrap.songtrend.entity.Timeserie;
import com.soundtrap.songtrend.utils.Utils;

import org.springframework.core.convert.converter.Converter;

@Component
public class DTOtoTimeserieConverter implements Converter<TimeserieDTO, Timeserie>{

	@Override
	public Timeserie convert(TimeserieDTO dto) {
		Timeserie timeserie = new Timeserie();
		if(dto.getId() != null && !StringUtils.isEmpty(dto.getId())) {
			timeserie.setTimeserieId(new Long(dto.getId()));
		}
		timeserie.setCount(dto.getCount());
		timeserie.setCountDate(Utils.getDateInMillis(dto.getDate()));
		return timeserie;
	}

}
