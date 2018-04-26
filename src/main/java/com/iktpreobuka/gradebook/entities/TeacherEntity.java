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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value = "TEACHER_ROLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeacherEntity extends UserEntity {
	
	/********************************************************************
	 *   				VEZA SA PREDMETOM
	 *   				*subject *teacher
	 ********************************************************************/
	@ManyToMany(cascade = CascadeType.REFRESH, 
            fetch = FetchType.LAZY) 
	@JoinTable(name = "TEACHER_SUBJECT",
			 joinColumns = {@JoinColumn(name = "teacher_id", 
			  nullable = false, 
			  updatable = false)
		     }, 
			 inverseJoinColumns = { @JoinColumn(name ="subject_id" , 
			     nullable = false, 
	             updatable = false) 
	         }
		) 
	//@JsonManagedReference(value="teacher_subject")
	@JsonIgnoreProperties("teacherEntity")
	protected Set<SubjectEntity> subjects = new HashSet<SubjectEntity>();
	
	/*********************************************************************
	 *   					VEZA SA NASTAVOM
	 *   			1 teacher list of teachings
	 *   
	 *********************************************************************/
	@OneToMany(mappedBy = "teacherFK", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference(value="teacher_teach")
	protected Set<TeachingEntity> teachings = new HashSet<TeachingEntity>();  
	
	
	public TeacherEntity(){
		
	}

	
	//get and set list of subjects

	public Set<SubjectEntity> getSubjects() {
		return subjects;
	}


	public void setSubjects(Set<SubjectEntity> subjects) {
		this.subjects = subjects;
	}
	
	public void addSubject(SubjectEntity subject) {
		this.subjects.add(subject);
		subject.getTeachers().add(this);
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
		if (teaching!=null && !(this.equals(teaching.getTeacherFK()))){
			teaching.setTeacherFK(this);
		}
	}

}
