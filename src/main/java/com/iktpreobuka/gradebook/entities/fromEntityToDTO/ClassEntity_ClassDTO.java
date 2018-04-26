package com.iktpreobuka.gradebook.entities.fromEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iktpreobuka.gradebook.entities.ClassEntity;
import com.iktpreobuka.gradebook.entities.dto.ClassDTO;
import com.iktpreobuka.gradebook.entities.type.ClassTypeToString;

@Component
public class ClassEntity_ClassDTO implements Converter<ClassEntity, ClassDTO> {
	@Autowired
	ClassTypeToString ctts;

	@Override
	/**convert one class from database to frontend*/
	public ClassDTO convert(ClassEntity classEn) {
		ClassDTO dto=new ClassDTO();
		dto.setId(classEn.getId());
		dto.setYear(classEn.getYear());
		dto.setClassType(ctts.convert(classEn.getClassType()));
		return dto;
	}
	
	
	/**convert list of classes from database to frontend*/
	public List<ClassDTO> convert(List<ClassEntity> classes){
		List<ClassDTO> listClasses=new ArrayList<>();
		for (ClassEntity c : classes) {
			listClasses.add(convert(c));
		}
		return listClasses;
	}
	
	
	
	

}
