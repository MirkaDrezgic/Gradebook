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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SUBJECT",
uniqueConstraints = {@UniqueConstraint(
        columnNames = {"subject_name", "classFK"},
        name="uk_subject_class")})
@Where(clause = "is_active <> '0'")
public class SubjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subject_id")
	private Integer id;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "weekly_hours")
	private Integer weeklyHours;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Version
	private Integer version;
	
	
	/*******************************************
	 *     			VEZA SA RAZREDOM
	 *     			1class *subjects
	 *******************************************/
	@ManyToOne(fetch= FetchType.LAZY,
			  cascade=CascadeType.REFRESH 
	)
	@JoinColumn(name ="classFK")
	@JsonBackReference(value="class_subjects")
	protected ClassEntity classFK = new ClassEntity();
	
	
	/*******************************************
	 *    			VEZA SA NASTAVOM
	 *   		 1 subject *list of teachings
	 *    
	 *******************************************/
	
	@OneToMany(mappedBy = "subjectFK", 
			  fetch= FetchType.LAZY,
			  cascade= { CascadeType.REFRESH }
	) 
	@JsonBackReference(value="teach_subject")
	protected Set<TeachingEntity> teachings = new HashSet<TeachingEntity>(); 
	
	/*******************************************
	 *   			VEZA SA NASTAVNIKOM
	 *   			*subject *teacher
	 *******************************************/
	
	@ManyToMany(cascade = CascadeType.REFRESH, 
            fetch = FetchType.LAZY) 
	@JoinTable(name = "TEACHER_SUBJECT",
			 joinColumns = {@JoinColumn(name = "subject_id", 
			  nullable = false, 
			  updatable = false)
		     }, 
			 inverseJoinColumns = { @JoinColumn(name = "teacher_id", 
			     nullable = false, 
	             updatable = false) 
	         }
		) 
	@JsonIgnoreProperties("subjectEntity")
	//@JsonBackReference(value="teacher_subject")
	protected Set<TeacherEntity> teachers = new HashSet<TeacherEntity>();
	
	

	public SubjectEntity() {
		super();
		}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(Integer weeklyHours) {
		this.weeklyHours = weeklyHours;
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

	
	//get i set class
	
	public ClassEntity getClassFK() {
		return classFK;
	}

	public void setClassFK(ClassEntity classFK) {
		this.classFK = classFK;
		if(classFK!=null && !(classFK.getSubjects().contains(this))){
			classFK.getSubjects().add(this);
		}
	}
	
	
	
	//get and set list of teachers

	public Set<TeacherEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeacherEntity> teachers) {
		this.teachers = teachers;
	}
	public void addTeacher(TeacherEntity teacher) {
		this.teachers.add(teacher);
	}
	
	
	//get and set list of TEACHINGS
	
	
	public Set<TeachingEntity> getTeachings() {
		return teachings;
	}

	public void setTeachings(Set<TeachingEntity> teachings) {
		this.teachings = teachings;
	}
	
	public void addTeaching(TeachingEntity teaching){
		this.teachings.add(teaching);
		if(teaching!=null && !(this.equals(teaching.getSubjectFK()))){
			teaching.setSubjectFK(this);
		}
	}
	
	
}
