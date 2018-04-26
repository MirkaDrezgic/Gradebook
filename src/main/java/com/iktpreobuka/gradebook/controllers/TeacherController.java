package com.iktpreobuka.gradebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.gradebook.entities.TeacherEntity;
import com.iktpreobuka.gradebook.repositories.TeacherRepository;
import com.iktpreobuka.gradebook.util.RESTError;

@RestController
@RequestMapping(path = "gradebook/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
	

	/************************************************************************
	 * 						GET gradebook/teacher/{id}
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllTeacher() {
		
		return new ResponseEntity< List<TeacherEntity> >( (List<TeacherEntity>) teacherRepository.findAll(),
																						HttpStatus.OK);
	}

	/************************************************************************
	 *						 GET gradebook/teacher/{id}
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
		try {
			TeacherEntity teacherDB = teacherRepository.findOne(id);
			if (teacherDB==null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Teacher with id " + id + " not found"),
													 HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TeacherEntity>(teacherDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2,"Exception occurred: "+e.getMessage()),
																HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	/************************************************************************
	 * 
	 * 						POST gradebook/teacher
	 * 
	 * 							@body teacher
	 ************************************************************************/
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveNewTeacher(@RequestBody TeacherEntity teacher) {
		
		teacherRepository.save(teacher);
		return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.CREATED);
	}
	
	
	/************************************************************************
	 * 					POST gradebook/teacher/{id}
	 * 
	 * 					@body teacher
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherEntity teacher) {
		TeacherEntity teacherDB = teacherRepository.findOne(id);
		
		if (teacherDB!=null) {
			if (teacher.getFirstName()!=null) {
				teacherDB.setFirstName(teacher.getFirstName());
			}
			if (teacher.getLastName()!=null) {
				teacherDB.setLastName(teacher.getLastName());
			}
			if (teacher.getEmail()!=null) {
				teacherDB.setEmail(teacher.getEmail());
			}
			if (teacher.getPassword()!=null) {
				teacherDB.setPassword(teacher.getPassword());	
			}
			if (teacher.getJmbg()!=null) {
				teacherDB.setJmbg(teacher.getJmbg());
			}
			if (teacher.getAddress()!=null) {
				teacherDB.setAddress(teacher.getAddress());
			}
			teacherRepository.save(teacherDB);
			return new ResponseEntity<TeacherEntity>(teacherDB, HttpStatus.OK);
		}else{
			return new ResponseEntity<RESTError>(new RESTError(1, "Teacher with id "+id+"not found"),
												HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	/************************************************************************
	 * 
	 * 						DELETE gradebook/teacher/{id}
	 * 
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
		TeacherEntity teacherDB = teacherRepository.findOne(id);
		if (teacherDB==null) {
			return new ResponseEntity<TeacherEntity>(HttpStatus.NOT_FOUND);
		}
		teacherRepository.delete(teacherDB);
		return new ResponseEntity<TeacherEntity>(teacherDB, HttpStatus.OK);
	}
}
