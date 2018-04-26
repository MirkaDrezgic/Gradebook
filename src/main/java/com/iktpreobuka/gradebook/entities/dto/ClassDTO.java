package com.iktpreobuka.gradebook.entities.dto;

public class ClassDTO {
	
	private Integer id;
	private String classType;
	private String year;
	
	public ClassDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
}
