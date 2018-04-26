package com.iktpreobuka.gradebook.services;

import java.util.List;

import com.iktpreobuka.gradebook.entities.SectionEntity;
import com.iktpreobuka.gradebook.entities.type.ClassType;
import com.iktpreobuka.gradebook.entities.type.SectionType;

public interface SectionDao {
	
	public Long countSectionsForClass(Integer idClass);
	
	public List<SectionEntity> findAllSection(String year, ClassType labelClass);
	
	public SectionEntity findSection(ClassType labelClass, String year, SectionType labelSection);

}
