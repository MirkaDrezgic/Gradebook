package com.iktpreobuka.gradebook.entities.fromDTOtoEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.iktpreobuka.gradebook.entities.StudentEntity;

import com.iktpreobuka.gradebook.entities.dto.StudentDTO;
import com.iktpreobuka.gradebook.repositories.ParentRepository;
import com.iktpreobuka.gradebook.repositories.StudentRepository;

@Component
public class StudentDTO_StudentEntity implements Converter<StudentDTO, StudentEntity>  {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ParentRepository parentRepository;
	
	
	@Override
	public StudentEntity convert(StudentDTO dto) {

		StudentEntity student = new StudentEntity();

		if (dto.getId() != null) {
			student=studentRepository.findOne(dto.getId());
			if(student==null){
				throw new IllegalStateException("Tried to modify a non-existent user");
			}
		}

		student.setEmail(dto.getEmail());
		//user.setPassword(dto.getPassword());
		student.setJmbg(dto.getJmbg());
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setAddress(dto.getAddress());
		
		return student;
	}
	
	
	/**convert list of users from database to frontend**/
	public List<StudentEntity> convert(List<StudentDTO> students){
		
		List<StudentEntity> listOfDTO=new ArrayList<>();
		for (StudentDTO student : students) {
			listOfDTO.add(convert(student));
		}
		return listOfDTO;
	}


}
