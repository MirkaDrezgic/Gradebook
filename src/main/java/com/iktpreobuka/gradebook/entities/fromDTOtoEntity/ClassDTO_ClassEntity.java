package com.iktpreobuka.gradebook.entities.fromDTOtoEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iktpreobuka.gradebook.entities.ClassEntity;
import com.iktpreobuka.gradebook.entities.dto.ClassDTO;
import com.iktpreobuka.gradebook.entities.type.StringToClassType;
import com.iktpreobuka.gradebook.repositories.ClassRepository;
@Component
public class ClassDTO_ClassEntity implements Converter<ClassDTO, ClassEntity> {

	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	StringToClassType stct;
	
	@Override
	/**convert class from database to frontend**/
	public ClassEntity convert(ClassDTO dto) {
		ClassEntity newClass=new ClassEntity();
		if(dto.getId()!=null){
			newClass= classRepository.findOne(dto.getId());
			if(newClass==null){
				throw new IllegalStateException("Tried to modify a non-existent class");
			}
		}
		newClass.setYear(dto.getYear());
		newClass.setClassType(stct.convert(dto.getClassType()));
		return newClass;
	}
	
	/**convert list of classes from database to frontend**/
	public List<ClassEntity> convert(List<ClassDTO> classDTO){
		List<ClassEntity> listOfEntity=new ArrayList<>();
		for (ClassDTO dto : classDTO) {
			listOfEntity.add(convert(dto));
		}
		return listOfEntity;
	}
}
