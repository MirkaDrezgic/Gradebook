package com.iktpreobuka.gradebook.entities.fromDTOtoEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.iktpreobuka.gradebook.entities.SectionEntity;

import com.iktpreobuka.gradebook.entities.dto.SectionDTO;

import com.iktpreobuka.gradebook.entities.type.StringToSectionType;
import com.iktpreobuka.gradebook.repositories.ClassRepository;
import com.iktpreobuka.gradebook.repositories.SectionRepository;

@Component
public class SectionDTO_SectionEntity implements Converter<SectionDTO, SectionEntity> {

	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private StringToSectionType stct;
	
	@Override
	/**convert class from database to frontend**/
	
	public SectionEntity convert(SectionDTO dto) {
		SectionEntity newSection;
		if(dto.getId()!=null){
			newSection= sectionRepository.findOne(dto.getId());	
		}
		else{
			newSection=new SectionEntity();
			newSection.setClassEntity(classRepository.findOne(dto.getIdClass()));
		}	
		newSection.setSectionType(stct.convert(dto.getSectionType()));
		return newSection;
	}
	
	/**convert list of classes from database to frontend**/
	
	public List<SectionEntity> convert(List<SectionDTO> sectionDTO){
		List<SectionEntity> listOfEntity=new ArrayList<>();
		for (SectionDTO dto : sectionDTO) {
			listOfEntity.add(convert(dto));
		}
		return listOfEntity;
	}

}
