package com.iktpreobuka.gradebook.entities.fromEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iktpreobuka.gradebook.entities.UserEntity;
import com.iktpreobuka.gradebook.entities.dto.UserDTO;

@Component
public class UserEntity_UserDTO implements Converter<UserEntity, UserDTO> {

	@Override
	/**convert one user from database to frontend*/
	public UserDTO convert(UserEntity user) {
		UserDTO userDTO=new UserDTO();
		if(user!=null){
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setPassword(user.getPassword());
			userDTO.setAddress(user.getAddress());
			userDTO.setJmbg(user.getJmbg());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
		}
		return userDTO;
	}
	
	/**convert list of users from database to frontend**/
	public List<UserDTO> convert(List<UserEntity> users){
		
		List<UserDTO> listUsers=new ArrayList<>();
		for (UserEntity user : users) {
			listUsers.add(convert(user));
		}
		return listUsers;
	}
	
	
	

}
