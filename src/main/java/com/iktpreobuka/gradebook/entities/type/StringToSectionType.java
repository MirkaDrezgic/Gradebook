package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToSectionType implements Converter<String, SectionType> {

	@Override
	/*******************************************
	 * Convert String to SectionType
	 *******************************************/
	public SectionType convert(String str) {
		
			switch (str) {
			case "a":
				return SectionType.a;
			case "b":
				return SectionType.b;
			case "c":
				return SectionType.c;
			case "d":
				return SectionType.d;
			case "e":
				return SectionType.e;
			case "f":
				return SectionType.f;	
			case "g":
				return SectionType.g;
			case "h":
				return SectionType.h;
			default:
				throw new IllegalArgumentException("Unknown value: " + str);
			}
		
	}

}
