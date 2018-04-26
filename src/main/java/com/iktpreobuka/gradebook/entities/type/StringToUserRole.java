package com.iktpreobuka.gradebook.entities.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToUserRole implements Converter<String, UserRole>{
	
	/**
	 * Convert String to enumeration UserRole
	 * */
	public UserRole convert(String str) {
		switch (str) {
		case "Administrator":
		case "ADMINISTRATOR":
			return UserRole.ADMIN_ROLE;
		case "Nastavnik":
		case "NASTAVNIK":
			return UserRole.TEACHER_ROLE;
		case "Učenik":
		case "UČENIK":
			return UserRole.STUDENT_ROLE;
		case "Roditelj":
		case "RODITELJ":
			return UserRole.PARENT_ROLE;
		default:
			throw new IllegalArgumentException("Unknown value: " + str);
		}
	}

}
