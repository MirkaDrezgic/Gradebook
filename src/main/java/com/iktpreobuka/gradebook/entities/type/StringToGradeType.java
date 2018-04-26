package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToGradeType implements Converter<String, GradeType> {

	@Override
	/**************************************
	 * Convert String to GradeType
	 **************************************/
	public GradeType convert(String str) {
		switch (str) {
		case "KONTROLNI":
		case "kontrolni":
			return GradeType.kontrolni;
		case "PISMENI":
		case "pismeni":
			return GradeType.pismeni;
		case "USMENI":
		case "usmeni":
			return GradeType.usmeni;
		case "ZAVRSNA":
		case "zavrsna":
			return GradeType.zavrsna;
		default:
			throw new IllegalArgumentException("Unknown value: " + str);
		}
		
	}

}
