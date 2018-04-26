package com.iktpreobuka.gradebook.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.gradebook.entities.type.ClassType;

@Entity
@Table(name="CLASS",
	   uniqueConstraints = {@UniqueConstraint(
	          columnNames = {"year", "classType"},
	          name="uk_class_year")})
@Where(clause = "is_active <> '0'")	  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClassEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "class_id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="classType")
	private ClassType classType;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Version
	private Integer version;
	
	/*******************************************************************
	 * 			  VEZA SA ODELJENJEM
	 * 			(lista odeljenja koja pripadaju ovom razredu)
	 * 			*list sections 1class
	 ********************************************************************/
	@OneToMany(mappedBy = "classEntity", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference(value="class_sections")
	protected Set<SectionEntity> sections = new HashSet<SectionEntity>(); 
	
	/*******************************************************************
	 * 			VEZA SA PREDMETOM
	 * 			(lista predmeta koji pripadaju razredu)
	 *			 *list of subjects 1class
	 ******************************************************************/
	@OneToMany(mappedBy = "classFK", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference(value="class_subjects")
	protected  Set<SubjectEntity> subjects = new HashSet<SubjectEntity>();
	
	
	
	public ClassEntity() {
		this.isActive=true;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}
	
	
	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}
	

    //get and set list of sections
	
	public Set<SectionEntity> getSections() {
		return sections;
	}


	public void setSections(Set<SectionEntity> sections) {
		this.sections = sections;
	}


	public void addSection(SectionEntity section) {
		this.sections.add(section);
		if(!(this.equals(section.getClassEntity()))){
			section.setClassEntity(this);
		}
	}

	
	//get and set of list of subjects

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public void addSubject(SubjectEntity subject) {
		this.subjects.add(subject);
		if(!(this.equals(subject.getClassFK()))){
			subject.setClassFK(this);
		}
	}
	
}
