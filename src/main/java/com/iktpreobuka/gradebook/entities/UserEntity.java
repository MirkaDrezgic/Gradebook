package com.iktpreobuka.gradebook.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Where;
//import org.hibernate.validator.constraints.Email;

//import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

@Table(name = "USER",
uniqueConstraints = {@UniqueConstraint(
	       columnNames = {"email", "user_type"},
	       name="uk_user_email")}
)
@Where(clause = "is_active <> '0'")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", 
					 discriminatorType = DiscriminatorType.STRING)

public class UserEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	//@Email
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	
	@Column(name = "JMBG", unique = true, nullable = false)
	private String jmbg;
	
	@Column(name = "address")
	private String address;

	
	@Column(name = "is_active")
	private boolean isActive;
	
	
	@Version
	private Integer version;

	// Konstruktor

	public UserEntity() {
		this.isActive=true;
	}

	 
	public UserEntity(UserEntity newUser){
		this.id=newUser.getId();
		this.firstName=newUser.getFirstName();
		this.lastName=newUser.getLastName();
		this.email=newUser.getEmail();
		this.password=newUser.getPassword();
		this.jmbg=newUser.getJmbg();
		this.address=newUser.getAddress();
		this.isActive=true;
	}

	// GET i SET metode
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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

	public void activate() {
		this.isActive = true;
	}
	
	public void deactivate(){
		this.isActive = false;
	}
	


}
