package com.iktpreobuka.gradebook.entities.fromEntityToDTO;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.iktpreobuka.gradebook.entities.SectionEntity;
import com.iktpreobuka.gradebook.entities.dto.SectionDTO;
import com.iktpreobuka.gradebook.entities.type.ClassTypeToString;
import com.iktpreobuka.gradebook.entities.type.SectionTypeToString;

@Component
public class SectionEntity_SectionDTO implements Converter<SectionEntity, SectionDTO> {
	
	@Autowired
	private SectionTypeToString stst;
	@Autowired
	private ClassTypeToString ctts;
	
	@Override
	public SectionDTO convert(SectionEntity section) {
		
		SectionDTO dto=new SectionDTO();
		dto.setId(section.getId());
		dto.setSectionType(stst.convert(section.getSectionType()));
		
		dto.setIdClass(section.getClassEntity().getId());
		dto.setClassType(ctts.convert(section.getClassEntity().getClassType()));
		dto.setYear(section.getClassEntity().getYear());
		return dto;
	}

	
	/**convert list of section from database to frontend**/
	public List<SectionDTO> convert(List<SectionEntity> sections){
		List<SectionDTO> listSections=new ArrayList<>();
		for (SectionEntity section : sections) {
			listSections.add(convert(section));
		}
		return listSections;
	}
}
