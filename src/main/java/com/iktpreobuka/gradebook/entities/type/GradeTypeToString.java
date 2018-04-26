package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class GradeTypeToString implements Converter<GradeType, String> {

	@Override
	/*********************************************
	 * Convert enumeration GradeType to String
	 *********************************************/
	public String convert(GradeType type) {
		switch (type) {
		case kontrolni:
			return "kontrolni";
		case pismeni:
			return "pismeni";
		case usmeni:
			return "usmeni";
		case zavrsna:
			return "zavrsna";
		default:
			throw new IllegalArgumentException("Unknown value: " + type);
		}
		
	}

}
