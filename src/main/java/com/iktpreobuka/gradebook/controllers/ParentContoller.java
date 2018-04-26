package com.iktpreobuka.gradebook.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.iktpreobuka.gradebook.entities.ParentEntity;
import com.iktpreobuka.gradebook.repositories.ParentRepository;
import com.iktpreobuka.gradebook.util.RESTError;



@RestController
@RequestMapping(path = "gradebook/parent")
public class ParentContoller {

	
	
	@Autowired
	private ParentRepository parentRepository;
	
	
	

	/************************************************************************
	 * 						GET gradebook/parent
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllParents() {
		return new ResponseEntity< List<ParentEntity> >( (List<ParentEntity>) parentRepository.findAll(),
																						HttpStatus.OK);
	}
	
	

	/************************************************************************
	 *						 GET gradebook/parent/{id}
	 ************************************************************************/
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Integer id) {
		try {
			ParentEntity parentDB = parentRepository.findOne(id);
			if (parentDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Parent with id " + id + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ParentEntity>(parentDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

	
	/************************************************************************
	 * 						DELETE gradebook/parent/{id}
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable Integer id) {
		ParentEntity parentDB = parentRepository.findOne(id);
		if (parentDB == null) {
			return new ResponseEntity<ParentEntity>(HttpStatus.NOT_FOUND);
		}
		parentDB.deactivate();
		parentRepository.save(parentDB);
		return new ResponseEntity<ParentEntity>(parentDB, HttpStatus.OK);
	}
	
}
