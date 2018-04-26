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

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@DiscriminatorValue(value = "PARENT_ROLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Where(clause = "is_active <> '0'")


public class ParentEntity extends UserEntity {
	
	
	/*******************************************
	 * 			VEZA SA UCENIKOM
	 * 			*student *parent
	 *******************************************/
	@ManyToMany(cascade = CascadeType.REFRESH, 
            fetch = FetchType.LAZY)  
	@JoinTable
	(name = "STUDENT_PARENT",    
	joinColumns = {@JoinColumn(name = "Parent_id", 
	               nullable = false, 
	               updatable = false)
     }, 
	inverseJoinColumns = { @JoinColumn(name = "Student_id", 
			     nullable = false, 
	             updatable = false) 
	         }
	) 
	//@JsonManagedReference(value="stud_parent")
	@JsonIgnoreProperties("parentEntity")
	protected Set<StudentEntity> students = new HashSet<StudentEntity>(); 
	
	public ParentEntity(){
		super();
	}

	
	//get and set list of students
	
	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	
	public void addStudent(StudentEntity student) {
		this.students.add(student);
		student.getParents().add(this);
	}

	
	
	
		
	
	
	
}
