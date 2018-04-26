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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Where;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.gradebook.entities.type.SectionType;


@Entity
@Table(name="SECTION",
uniqueConstraints = {@UniqueConstraint(
        columnNames = {"sectionType", "classEntity"},
        name="uk_section_class")})
@Where(clause = "is_active <> '0'")
public class SectionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "section_id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="sectionType")
	private SectionType sectionType;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Version
	private Integer version;

	/*********************************************************
	 * 			VEZA SA RAZREDOM
	 * 			(odeljenje pripada tacno jednom razredu
	 * 			1 class *list sections
	 * 
	 * kompozicija
	 * odeljenje slab tip podataka ne moze se 
	 * identifikovati bez razreda
	 ********************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="classEntity")
	@JsonBackReference(value="class_sections")
	protected ClassEntity classEntity;
	
	
	/********************************************************
	 * 				VEZA SA UCENIKOM
	 * 			(odeljenje ima listu ucenika)
	 * 			1 section list of students
	 * 
	 *******************************************************/
	@OneToMany(mappedBy = "section", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference("stud_section")
	protected Set<StudentEntity> students = new HashSet<StudentEntity>(); 
	
	/****************************************************
	 * 				VEZA SA NASTAVOM
	 * 		(odeljenje ima listu izvodjenja nastave)
	 * 			1 section *list of teachings
	 *****************************************************/
	@OneToMany(mappedBy = "sectionFK", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference(value="teach_section")
	protected Set<TeachingEntity> teachings = new HashSet<TeachingEntity>(); 

	
	
	public SectionEntity() {
		this.isActive=true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SectionType getSectionType() {
		return sectionType;
	}

	public void setSectionType(SectionType sectionType) {
		this.sectionType = sectionType;
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

	
	
	//set i get class
	
	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
		if (classEntity!=null && !(classEntity.getSections().contains(this))) {
			classEntity.getSections().add(this);
		}
	}
	
	//get i set list of students

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}
	
	public void addStudent(StudentEntity student){
		this.students.add(student);
		if(student!=null && !(this.equals(student.getSection()))){
			student.setSection(this);
		}
	}
	


	//get and set list of teachings
	
	
	public Set<TeachingEntity> getTeachings() {
		return teachings;
	}

	public void setTeachings(Set<TeachingEntity> teachings) {
		this.teachings = teachings;
	}
	
	public void addTeaching(TeachingEntity teaching){
		this.teachings.add(teaching);
		if(teaching!=null && !(this.equals(teaching.getSectionFK()))){
			teaching.setSectionFK(this);
		}
	}
}
