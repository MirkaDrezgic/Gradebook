package com.iktpreobuka.gradebook.entities.type;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRoleToString implements Converter<UserRole, String> {
	
	@Override
	/**
	 * Convert enumeration UserRole to String
	 * */
	public String convert(UserRole role) {
		switch (role) {
		case ADMIN_ROLE:
			return "Administrator";
		case TEACHER_ROLE:
			return "Nastavnik";
		case STUDENT_ROLE:
			return "Učenik";
		case PARENT_ROLE:
			return "Roditelj";
		default:
			throw new IllegalArgumentException("Unknown value: " + role);
		}
	}


	



}
