package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToClassType implements Converter<String, ClassType> {

	@Override
	/******************************************
	 * Convert String to ClassType
	 ******************************************/
	public ClassType convert(String str) {
		switch (str) {
		case "I":
			return ClassType.I;
		case "II":
			return ClassType.II;
		case "III":
			return ClassType.III;
		case "IV":
			return ClassType.IV;
		case "V":
			return ClassType.V;
		case "VI":
			return ClassType.VI;
		case "VII":
			return ClassType.VII;
		case "VIII":
			return ClassType.VIII;
		default:
			throw new IllegalArgumentException("Unknown value: " + str);
		}
	}

}
