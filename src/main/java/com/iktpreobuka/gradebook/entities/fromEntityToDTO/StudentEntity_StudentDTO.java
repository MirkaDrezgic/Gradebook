package com.iktpreobuka.gradebook.entities.fromEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.iktpreobuka.gradebook.entities.StudentEntity;

import com.iktpreobuka.gradebook.entities.dto.StudentDTO;

@Component
public class StudentEntity_StudentDTO implements Converter<StudentEntity, StudentDTO> {

	@Override
	/**convert one student from database to frontend*/
	public StudentDTO convert(StudentEntity student) {
		StudentDTO studentDTO=new StudentDTO();
		if(student!=null){
			studentDTO.setId(student.getId());
			studentDTO.setEmail(student.getEmail());
			studentDTO.setPassword(student.getPassword());
			studentDTO.setAddress(student.getAddress());
			studentDTO.setJmbg(student.getJmbg());
			studentDTO.setFirstName(student.getFirstName());
			studentDTO.setLastName(student.getLastName());
			if(student.getParents()!=null){
				studentDTO.setParents(student.getParents());
			}
			if(student.getSection()!=null){
				studentDTO.setSection(student.getSection());
			}
			
		}
		return studentDTO;
	}
	
	/**convert list of students from database to frontend**/
	public List<StudentDTO> convert(List<StudentEntity> students){
		
		List<StudentDTO> listStudent=new ArrayList<>();
		for (StudentEntity student : students) {
			listStudent.add(convert(student));
		}
		return listStudent;
	}

	
	
}
