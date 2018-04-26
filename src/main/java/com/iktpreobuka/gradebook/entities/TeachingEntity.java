package com.iktpreobuka.gradebook.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="TEACHING")
@Where(clause = "is_active <> '0'")
public class TeachingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teach_id")
	private Integer id;
	
	@Column(name = "end_semestar")
	private boolean endSemestar;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Version
	private Integer version;
	
	/***************************************************************
	 *			 VEZA SA ODELJENJEM
	 *			 1 section *list of teachings
	 * 
	 **************************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="sectionFK")
	@JsonBackReference(value="teach_section")
	protected SectionEntity sectionFK;
	
	/**************************************************************
	 * 				VEZA SA PREDMETOM
	 * 			1 subject *list of teachings
	 * 
	 *************************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="subjectFK")
	@JsonBackReference(value="teach_subject")
	protected SubjectEntity subjectFK;
	
	/*************************************************************
	 *				 VEZA SA NASTAVNIKOM
	 *			1 teacher list of teachings
	 ************************************************************/
	@ManyToOne(cascade = CascadeType.REFRESH, 
	           fetch = FetchType.LAZY) 
	@JoinColumn(name ="teacherFK")
	@JsonBackReference(value="teacher_teach")
	protected TeacherEntity teacherFK;
	
	
	/**************************************************************
	 * 					VEZA SA OCENAMA
	 * 			list of grades 1 teaching plan
	 * 
	 *************************************************************/
	@OneToMany(mappedBy = "teach", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonManagedReference("stud_teach")
	protected Set<GradeEntity> grades = new HashSet<GradeEntity>();
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isEndSemestar() {
		return endSemestar;
	}
	public void setEndSemestar(boolean endSemestar) {
		this.endSemestar = endSemestar;
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
	
	//get and set sectionFK
	
	public SectionEntity getSectionFK() {
		return sectionFK;
	}
	public void setSectionFK(SectionEntity sectionFK) {
		this.sectionFK = sectionFK;
		if(sectionFK!=null && !(sectionFK.getTeachings().contains(this))){
			sectionFK.getTeachings().add(this);
		}
	}
	
	
	//get i set subjectFK
	
	public SubjectEntity getSubjectFK() {
		return subjectFK;
	}
	public void setSubjectFK(SubjectEntity subjectFK) {
		this.subjectFK = subjectFK;
		if(subjectFK!=null && !(subjectFK.getTeachings().contains(this))){
			subjectFK.getTeachings().add(this);
		}
	}
	
	//get and set teacherFK
	
	public TeacherEntity getTeacherFK() {
		return teacherFK;
	}
	public void setTeacherFK(TeacherEntity teacherFK) {
		this.teacherFK = teacherFK;
		if(teacherFK!=null && !(teacherFK.getTeachings().contains(this))){
			teacherFK.getTeachings().add(this);
		}
	} 
	
	//get and set list of grades
	
	public Set<GradeEntity> getGrades() {
		return grades;
	}
	public void setGrades(Set<GradeEntity> grades) {
		this.grades = grades;
	}
	public void addGrade(GradeEntity grade){
		this.grades.add(grade);
		if(grade!=null && !(this.equals(grade.getTeach()))){
			grade.setTeach(this);;
		}
	}

	

}
