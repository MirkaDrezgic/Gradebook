package com.iktpreobuka.gradebook.entities.fromDTOtoEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iktpreobuka.gradebook.entities.UserEntity;
import com.iktpreobuka.gradebook.entities.dto.UserDTO;
import com.iktpreobuka.gradebook.repositories.UserRepository;

@Component
public class UserDTO_UserEntity implements Converter<UserDTO, UserEntity> {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserEntity convert(UserDTO dto) {

		UserEntity user = new UserEntity();

		if (dto.getId() != null) {
			user=userRepository.findOne(dto.getId());
			if(user==null){
				throw new IllegalStateException("Tried to modify a non-existent user");
			}
		}

		user.setEmail(dto.getEmail());
		//user.setPassword(dto.getPassword());
		user.setJmbg(dto.getJmbg());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		return user;
	}
	
	
	/**convert list of users from database to frontend**/
	public List<UserEntity> convert(List<UserDTO> users){
		
		List<UserEntity> listOfDTO=new ArrayList<>();
		for (UserDTO user : users) {
			listOfDTO.add(convert(user));
		}
		return listOfDTO;
	}

}
