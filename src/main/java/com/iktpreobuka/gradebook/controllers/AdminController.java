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

import com.iktpreobuka.gradebook.entities.AdminEntity;
import com.iktpreobuka.gradebook.repositories.AdminRepository;
import com.iktpreobuka.gradebook.util.RESTError;


@RestController
@RequestMapping(path = "gradebook/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
//	@Autowired
//	private AdminDao adminService;

	/************************************************************************
	 * GET gradebook/admin
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllAdmins() {
		try {
			return new ResponseEntity<List<AdminEntity>>( (List<AdminEntity>) adminRepository.findAll() , HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 * GET gradebook/admin/{id}
	 ************************************************************************/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getAdminById(@PathVariable Integer id) {
		try {
			AdminEntity adminDB = (AdminEntity) adminRepository.findOne(id);
			if (adminDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Admin with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<AdminEntity>(adminDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 * POST gradebook/admin
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveNewAdmin(@RequestBody AdminEntity admin) {
		try {
//			if(adminService.existEmail(admin.getEmail())){
//				return new ResponseEntity<RESTError>(new RESTError(4, "This admin exist in database, try again"),
//																	HttpStatus.NOT_ACCEPTABLE);
//			}
			adminRepository.save(admin);
			if(admin.getPassword()==null){
				return new ResponseEntity<RESTError>(new RESTError(566, "Exception occurred: " ),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<AdminEntity>(admin, HttpStatus.CREATED);
		}catch (Exception e) {
		return new ResponseEntity<RESTError>(new RESTError(3, "Exception occurred: " + e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	/************************************************************************
	 * PUT gradebook/admin/{id}
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> updateAdmin(@PathVariable Integer id, @RequestBody AdminEntity admin) {
		try {
			AdminEntity adminDB = adminRepository.findOne(id);
			if (adminDB != null) {
				if (admin.getFirstName() != null) {
					adminDB.setFirstName(admin.getFirstName());
				}
				if (admin.getLastName() != null) {
					adminDB.setLastName(admin.getLastName());
				}
				if (admin.getEmail() != null) {
					adminDB.setEmail(admin.getEmail());
					
				}
				if (admin.getPassword() != null) {
					adminDB.setPassword(admin.getPassword());
				}
				if (admin.getJmbg() != null) {
					adminDB.setJmbg(admin.getJmbg());
				}
				if (admin.getAddress() != null) {
					adminDB.setAddress(admin.getAddress());
				}
				adminRepository.save(adminDB);
				return new ResponseEntity<AdminEntity>(adminDB, HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "Admin with id " + id + "not found"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(4, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 * DELETE gradebook/admin/{id}
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
		try {
			AdminEntity adminDB = adminRepository.findOne(id);
			if (adminDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Admin with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			adminDB.setEmail(adminDB.getEmail()+"old email");
			adminDB.setJmbg(adminDB.getJmbg()+"old data");
			adminDB.deactivate();
			adminRepository.save(adminDB);
			return new ResponseEntity<AdminEntity>(adminDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
