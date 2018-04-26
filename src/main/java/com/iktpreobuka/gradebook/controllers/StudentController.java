package com.iktpreobuka.gradebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.iktpreobuka.gradebook.entities.ParentEntity;

import com.iktpreobuka.gradebook.entities.StudentEntity;

import com.iktpreobuka.gradebook.entities.dto.StudentDTO;
import com.iktpreobuka.gradebook.entities.fromEntityToDTO.StudentEntity_StudentDTO;
import com.iktpreobuka.gradebook.repositories.ParentRepository;
import com.iktpreobuka.gradebook.repositories.StudentRepository;
import com.iktpreobuka.gradebook.services.ParentDao;
import com.iktpreobuka.gradebook.util.RESTError;

@RestController
@RequestMapping(path = "gradebook/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private StudentEntity_StudentDTO stud;
//	@Autowired
//	private StudentDTO_StudentEntity dto;

	private ParentDao parentService;

	/************************************************************************
	 * GET gradebook/student
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllStudents(@RequestParam(value = "jmbg", required = false) String jmbg
//											@RequestParam(defaultValue="0") Integer pageNum
											) {
//		Page<StudentEntity> studPage=null;
		List<StudentEntity> students = null;
		if ((jmbg == null) || (jmbg.isEmpty())) {
			students= studentRepository.findAll();
		} else {
			students = studentRepository.findByJmbg(jmbg);
		}
//		students=studPage.getContent();
//		Integer totalPages=studPage.getTotalPages();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", String.valueOf(totalPages) );
//	return new ResponseEntity<List<StudentDTO>>(stud.convert(students), headers, HttpStatus.OK);
//		return new ResponseEntity<List<StudentEntity>>((List<StudentEntity>) studentRepository.findAll(),
//				HttpStatus.OK);
		return new ResponseEntity<List<StudentDTO>>(stud.convert(students),  HttpStatus.OK);
	}

	/************************************************************************
	 * GET gradebook/student/{id}
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
		try {
			StudentEntity studentDB = studentRepository.findOne(id);
			if (studentDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Student with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<StudentDTO>(stud.convert(studentDB), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 * POST gradebook/student
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveNewStudent(@RequestBody StudentEntity student) {
		try {
			studentRepository.save(student);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/************************************************************************
	 * PUT gradebook/student/{idStudent}/parent
	 * 
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/parent")
	public ResponseEntity<?> addParentToStudent(@PathVariable Integer id, @RequestBody ParentEntity parent) {
		try {
			StudentEntity studentDB = studentRepository.findOne(id);
			if (studentDB != null) {
				if (parent != null) {
					ParentEntity parentDB = new ParentEntity();
					if (parent.getId() != null) {
						for (ParentEntity p : studentDB.getParents()) {
							if (!p.getId().equals(parentDB.getId())) {
								studentDB.addParent(parent);
							}
						}
					}
					parentRepository.save(parent);
					studentDB.addParent(parent);
					studentRepository.save(studentDB);
				} else {
					return new ResponseEntity<RESTError>(new RESTError(3, "There are no data about the parent"),
							HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<RESTError>(new RESTError(2, "Student with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}

			studentRepository.save(studentDB);
			return new ResponseEntity<StudentEntity>(studentDB, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/************************************************************************
	 * PUT gradebook/student/{idStudent}/section
	 * 
	 ************************************************************************/
	
	/*@RequestMapping(method = RequestMethod.PUT, value = "/{id}/section")
	public ResponseEntity<?> addSection(@PathVariable Integer id, @RequestBody section ) {
		try {
			StudentEntity studentDB = studentRepository.findOne(id);
			if (studentDB != null) {
				if (section != null) {
					SectionEntity sectionDB=sectionRepository.findOne(section)
					if (sectionDB.getId() != null) {
						studentDB.setSection(sectionDB)
					}
					sectionRepository.save(sectionDB);
					studentDB.addSection(sectionDB);
					studentRepository.save(studentDB);
				} else {
					return new ResponseEntity<RESTError>(new RESTError(3, "There are no data about the parent"),
							HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<RESTError>(new RESTError(2, "Student with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}

			studentRepository.save(studentDB);
			return new ResponseEntity<StudentEntity>(studentDB, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	*/

//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentEntity student) {
//		StudentEntity studentDB = studentRepository.findOne(id);
//		if (studentDB != null) {
//			if (student.getFirstName() != null) {
//				studentDB.setFirstName(student.getFirstName());
//			}
//			if (student.getLastName() != null) {
//				studentDB.setLastName(student.getLastName());
//			}
//			if (student.getEmail() != null) {
//				studentDB.setEmail(student.getEmail());
//			}
//			if (student.getPassword() != null) {
//				studentDB.setPassword(student.getPassword());
//			}
//			if (student.getJmbg() != null) {
//				studentDB.setJmbg(student.getJmbg());
//			}
//			if (student.getAddress() != null) {
//				studentDB.setAddress(student.getAddress());
//			}
//			studentDB.addParent(new ParentEntity());
//			;
//			if (student.getParents() != null) {
//
//				studentDB.setParents(student.getParents());
//			}
//			ParentEntity parentDB = new ParentEntity();
//			parentDB.activate();
//
//			// zatim ga ubaci u presecnu tabelu
//			for (ParentEntity parent : studentDB.getParents()) {
//				if (!parent.getId().equals(parentDB.getId())) {
//					// setovanje izmenjenog usera
//				
//					parentRepository.save(parentDB);
//				}
//
//			}
//			if (parentDB != null) {
//				studentDB.addParent(parentDB);
//			}
//			studentRepository.save(studentDB);
//			return new ResponseEntity<StudentEntity>(studentDB, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<RESTError>(new RESTError(1, "Student with id " + id + "not found"),
//					HttpStatus.NOT_FOUND);
//		}
//
//	}

	// do logical deletion for student with forwarded id,
	// and do logical deletion for parents for this student, if this parent only
	// parent of all students
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		StudentEntity studentDB = studentRepository.findOne(id);
		if (studentDB == null) {
			return new ResponseEntity<StudentEntity>(HttpStatus.NOT_FOUND);
		}
		studentDB.deactivate();
		System.out.print(studentDB.getFirstName());
		for (ParentEntity parent : studentDB.getParents()) {
			if (parentService.countStudentsForParent(parent.getId()).equals(1l)) {
				parent.deactivate();
			}
		}
		studentRepository.save(studentDB);

		return new ResponseEntity<StudentEntity>(studentDB, HttpStatus.OK);
	}
}
