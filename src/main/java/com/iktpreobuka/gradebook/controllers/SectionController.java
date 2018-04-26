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
import com.iktpreobuka.gradebook.entities.SectionEntity;
import com.iktpreobuka.gradebook.entities.dto.SectionDTO;
import com.iktpreobuka.gradebook.entities.fromEntityToDTO.SectionEntity_SectionDTO;
import com.iktpreobuka.gradebook.entities.type.ClassType;
import com.iktpreobuka.gradebook.entities.type.StringToClassType;
import com.iktpreobuka.gradebook.entities.type.StringToSectionType;

import com.iktpreobuka.gradebook.repositories.SectionRepository;
import com.iktpreobuka.gradebook.services.SectionDaoImpl;
import com.iktpreobuka.gradebook.util.RESTError;

@RestController
@RequestMapping(path = "gradebook/section")
public class SectionController {

	@Autowired
	private SectionRepository sectionRepository;


	@Autowired
	private SectionEntity_SectionDTO sectionToDTO;

	@Autowired
	private SectionDaoImpl sectionService;

	@Autowired
	private StringToSectionType strToSection;

	@Autowired
	private StringToClassType strToClass;

	/************************************************************************
	 * GET gradebook/section list of all sections
	 * 
	 * GET gradebook/section/?year=...?labelClass=... list of section for class
	 * in @param year and with label @param labelClass
	 * 
	 * 
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllSections(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "labelClass", required = false) String labelClass) {

		List<SectionEntity> sections = new ArrayList<SectionEntity>();
		if (year != null && labelClass != null) {
			ClassType classType = strToClass.convert(labelClass);
	
				sections = sectionService.findAllSection(year, classType);

		
			System.out.println(sections.toString());
		} else {
			sections = (List<SectionEntity>) sectionRepository.findAll();
		}
		return new ResponseEntity<List<SectionDTO>>(sectionToDTO.convert(sections), HttpStatus.OK);

	}

	/************************************************************************
	 * 
	 * GET gradebook/section/{id}
	 * 
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getSectionById(@PathVariable Integer id) {
		try {
			SectionEntity sectionDB = sectionRepository.findOne(id);
			if (sectionDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Section with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<SectionDTO>(sectionToDTO.convert(sectionDB), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*****************************************************************************************
	 * GET gradebook/section/class/?year=...?labelClass=...?labelSection=...
	 * section with this label @param labelSection in this school year @param
	 * year in this class @param labelClass
	 *******************************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/class")
	public ResponseEntity<?> getSection(@RequestParam(value = "year") String year,
			@RequestParam(value = "labelClass") String labelClass,
			@RequestParam(value = "labelSection") String labelSection) {

		try {
			SectionEntity sectionDB = sectionService.findSection(strToClass.convert(labelClass), year,
					strToSection.convert(labelSection));
			if (sectionDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(4, "Section not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<SectionDTO>(sectionToDTO.convert(sectionDB), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
