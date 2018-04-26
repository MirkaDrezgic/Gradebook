package com.iktpreobuka.gradebook.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;





@Entity
@DiscriminatorValue(value = "ADMIN_ROLE")
public class AdminEntity extends UserEntity{
	
	public AdminEntity(){
		
	};
	

}
