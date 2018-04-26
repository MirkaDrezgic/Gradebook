package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class SectionTypeToString implements Converter<SectionType, String> {

	@Override
	/***********************************************
	 * Convert enumeration SectionType to String
	 ***********************************************/
	public String convert(SectionType section) {
		switch (section) {
		case a:
			return "a";
		case b:
			return "b";
		case c:
			return "c";
		case d:
			return "d";
		case e:
			return "e";
		case f:
			return "f";	
		case g:
			return "g";
		case h:
			return "h";
		default:
			throw new IllegalArgumentException("Unknown value: " + section);
		}
	}

}
