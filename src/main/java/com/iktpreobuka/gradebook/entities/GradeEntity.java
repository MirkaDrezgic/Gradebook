package com.iktpreobuka.gradebook.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.gradebook.entities.type.GradeType;

@Entity
@Table(name="GRADE")
@Where(clause = "is_active <> '0'")
public class GradeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grade_id")
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(name="grade_date")
	private Date gradeDate;
	
//	vrednost ocene
	@Column(name = "value")
	private String value;

//	vrsta ocene
	@Column(name = "subject_name")
	private GradeType gradeType;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Version
	private Integer version;
	
	
	/***********************************************************
	 *           	 VEZA SA UCENIKOM
	 *          1 student  *list of grade 
	 ***********************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="student")
	@JsonBackReference(value="stud_grade")
	protected StudentEntity student;
	
	
	/***********************************************************
	 * 					VEZA SA NASTAVOM
	 *         		1 teaching  *list of grade
	 *         
	 **********************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="teach")
	@JsonBackReference(value="stud_teach")
	protected TeachingEntity teach;
	
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getGradeDate() {
		return gradeDate;
	}


	public void setGradeDate(Date gradeDate) {
		this.gradeDate = gradeDate;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public GradeType getGradeType() {
		return gradeType;
	}


	public void setGradeType(GradeType gradeType) {
		this.gradeType = gradeType;
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


	//get and set of student
	
	public StudentEntity getStudent() {
		return student;
	}


	public void setStudent(StudentEntity student) {
		this.student = student;
		if((student!=null) && !(student.getGrades().contains(this))){
			student.getGrades().add(this);
		}
	}
	

	//get and set of teaching
	
	
	public TeachingEntity getTeach() {
		return teach;
	}


	public void setTeach(TeachingEntity teach) {
		this.teach = teach;
		if(teach!=null && !(teach.getGrades().contains(this))){
			teach.getGrades().add(this);
		}
		
	}
	

}
