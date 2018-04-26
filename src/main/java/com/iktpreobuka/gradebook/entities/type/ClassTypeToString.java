package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class ClassTypeToString implements Converter<ClassType, String> {

	@Override
	/*************************************************
	 * Convert enumeration ClassType to String
	 *************************************************/
	public String convert(ClassType type) {
		switch (type) {
		case I:
			return "I";
		case II:
			return "II";
		case III:
			return "III";
		case IV:
			return "IV";
		case V:
			return "V";
		case VI:
			return "VI";
		case VII:
			return "VII";
		case VIII:
			return "VIII";
		default:
			throw new IllegalArgumentException("Unknown value: " + type);
		}
	}

}
