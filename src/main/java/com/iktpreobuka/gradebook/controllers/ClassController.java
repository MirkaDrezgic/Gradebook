package com.iktpreobuka.gradebook.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.gradebook.entities.ClassEntity;
import com.iktpreobuka.gradebook.entities.SectionEntity;
import com.iktpreobuka.gradebook.entities.dto.ClassDTO;
import com.iktpreobuka.gradebook.entities.dto.SectionDTO;
import com.iktpreobuka.gradebook.entities.fromEntityToDTO.ClassEntity_ClassDTO;
import com.iktpreobuka.gradebook.entities.fromEntityToDTO.SectionEntity_SectionDTO;
import com.iktpreobuka.gradebook.entities.type.ClassType;
import com.iktpreobuka.gradebook.entities.type.StringToSectionType;
import com.iktpreobuka.gradebook.repositories.ClassRepository;
import com.iktpreobuka.gradebook.repositories.SectionRepository;
import com.iktpreobuka.gradebook.services.SectionDaoImpl;
import com.iktpreobuka.gradebook.util.RESTError;

@RestController
@RequestMapping(path = "gradebook/class")
public class ClassController {
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private ClassEntity_ClassDTO classToDTO;
	@Autowired
	private SectionEntity_SectionDTO sectionToDTO;
	@Autowired
	private SectionDaoImpl sectionService;

	@Autowired
	private StringToSectionType strToSectionType;

	/************************************************************************
	 * 				GET gradebook/class 
	 * 				list of all classes in DB
	 * 
	 * 				GET gradebook/class?year=...
	 * 				list of classes in this school year
	 * 
	 * 
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllClasses(@RequestParam(value = "year", required = false) String year) {
		List<ClassEntity> classes = null;
		
		if (year != null) {
			// list of classes in this school year
			classes = (List<ClassEntity>) classRepository.findByYear(year);
		} else {
			classes = (List<ClassEntity>) classRepository.findAll();
		}
		return new ResponseEntity<List<ClassDTO>>(classToDTO.convert(classes), HttpStatus.OK);

	}

	/************************************************************************
	 * 
	 * 						GET gradebook/class/{id}
	 * 
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getClassById(@PathVariable Integer id) {
		try {
			ClassEntity classDB = classRepository.findOne(id);
			if (classDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Class with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ClassDTO>(classToDTO.convert(classDB), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 *			 POST gradebook/class
	 * 
	 *				 @param year
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveNewClasses(@RequestParam String year) {
		List<ClassEntity> classList = new ArrayList<ClassEntity>();
		for (ClassType cl : ClassType.values()) {
			ClassEntity classDB = new ClassEntity();
			classDB.setClassType(cl);
			classDB.setYear(year);
			classRepository.save(classDB);
			classList.add(classDB);
		}

		return new ResponseEntity<List<ClassDTO>>(classToDTO.convert(classList), HttpStatus.CREATED);

	}



	/************************************************************************
	 * PUT gradebook/class/{idClass}/section
	 *
	 * @param sectionName
	 *            //dodavanje jednog odeljenja razredu
	 * 
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.PUT, value = "/{idClass}/section")
	public ResponseEntity<?> addSectionS(@PathVariable Integer idClass, @RequestParam String sectionName) {
		try {
			ClassEntity classDB = classRepository.findOne(idClass);
			SectionEntity sectionDB = new SectionEntity();
			if (sectionService.existsSectionForClass(idClass, strToSectionType.convert(sectionName))) {
				// ako vec postoji ovo odeljenje greska
				return new ResponseEntity<RESTError>(
						new RESTError(21, "Section with name " + sectionName + " exists in database"),
						HttpStatus.BAD_REQUEST);
			} else if (classDB == null) {
				// ako ne postoji razred greska
				return new ResponseEntity<RESTError>(new RESTError(1, "Class with id " + idClass + " not found"),
						HttpStatus.NOT_FOUND);
			} else {
				// inace napravi novo odeljenje
				sectionDB.setSectionType(strToSectionType.convert(sectionName));
				sectionDB.setClassEntity(classDB);
				classRepository.save(classDB);
				sectionRepository.save(sectionDB);

				return new ResponseEntity<SectionDTO>(sectionToDTO.convert(sectionDB), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	/************************************************************************
	 * 
	 * DELETE gradebook/class/{id}
	 * 
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteClass(@PathVariable Integer id) {
		ClassEntity classDB = classRepository.findOne(id);
		if (classDB == null) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Class with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		for (SectionEntity section : classDB.getSections()) {
				section.setActive(false);
		}
		classDB.setActive(false);
		classRepository.save(classDB);
		return new ResponseEntity<ClassDTO>(classToDTO.convert(classDB), HttpStatus.OK);
	}
}
