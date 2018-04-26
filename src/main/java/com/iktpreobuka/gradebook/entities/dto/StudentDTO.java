package com.iktpreobuka.gradebook.entities.dto;

import java.util.HashSet;
import java.util.Set;

import com.iktpreobuka.gradebook.entities.ParentEntity;
import com.iktpreobuka.gradebook.entities.SectionEntity;

public class StudentDTO extends UserDTO {
	
	protected Set<ParentEntity> parents = new HashSet<ParentEntity>();
	protected SectionEntity section;
	
	public Set<ParentEntity> getParents() {
		return parents;
	}
	public void setParents(Set<ParentEntity> parents) {
		this.parents = parents;
	}
	public SectionEntity getSection() {
		return section;
	}
	public void setSection(SectionEntity section) {
		this.section = section;
	}
}
