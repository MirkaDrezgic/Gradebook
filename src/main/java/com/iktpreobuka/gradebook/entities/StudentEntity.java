package com.iktpreobuka.gradebook.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@DiscriminatorValue(value = "STUDENT_ROLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentEntity extends UserEntity {
	

	
	
	

	/*******************************************
	 * 			VEZA SA RODITELJEM
	 *		 	*student *parent
	 *******************************************/
	
	@ManyToMany(cascade = CascadeType.REFRESH, 
            fetch = FetchType.LAZY) 
	@JoinTable(name = "STUDENT_PARENT",
			 joinColumns = {@JoinColumn(name = "Student_id", 
			  nullable = false, 
			  updatable = false)
		     }, 
			 inverseJoinColumns = { @JoinColumn(name = "Parent_id", 
			     nullable = false, 
	             updatable = false) 
	         }
		) 
	//@JsonManagedReference(value="stud_parent")
	@JsonIgnoreProperties("studentEntity")
	protected Set<ParentEntity> parents = new HashSet<ParentEntity>();
	
	
	/******************************************************************
	 * 				VEZA SA ODELJENJEM
	 * 			1 section list of students
	 ********************************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="section")
	@JsonBackReference(value="stud_section")
	protected SectionEntity section;
	
	
	/*******************************************************************
	 * 						VEZA SA OCENAMA
	 * 					*list of grade 1 student
	 *******************************************************************/
	@OneToMany(mappedBy = "student", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference(value="stud_grade")
	protected Set<GradeEntity> grades = new HashSet<GradeEntity>(); 
	

	public StudentEntity() {
		super();
		
	}


	
	//get and set list of parents
	
	
	public Set<ParentEntity> getParents() {
		return parents;
	}

	public void setParents(Set<ParentEntity> parents) {
		this.parents = parents;
	}
	

	public void addParent(ParentEntity parent) {
		this.parents.add(parent);
		
	}

	
	//get and set section
	
	public SectionEntity getSection() {
		return section;
	}

	public void setSection(SectionEntity section) {
		this.section = section;
		if(section!=null && !(section.getStudents().contains(this))){
			section.getStudents().add(this);
		}
	}

	
	
	//get and set grade

	public Set<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}
	public void addGrade(GradeEntity grade){
		this.grades.add(grade);
		if(grade!=null && !(this.equals(grade.getStudent()))){
			grade.setStudent(this);
		}
	}
	
	

	
	
	
	

}
