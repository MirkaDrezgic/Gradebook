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

import com.iktpreobuka.gradebook.entities.UserEntity;
import com.iktpreobuka.gradebook.repositories.UserRepository;
import com.iktpreobuka.gradebook.util.RESTError;



@RestController
@RequestMapping(path = "gradebook/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	

	

	/************************************************************************
	 *						 GET gradebook/user
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		
		
		return new ResponseEntity<List<UserEntity>>(
							(List<UserEntity>) userRepository.findAll(), 
							HttpStatus.OK);
	}

	/************************************************************************
	 * 						GET gradebook/user/{id}
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) {
		try {
			UserEntity userDB = userRepository.findOne(id);
			if (userDB == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "User with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UserEntity>(userDB, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/************************************************************************
	 * 								POST gradebook/user
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> saveNewUser(@RequestBody UserEntity newUser) {
		try {
			UserEntity userDB=new UserEntity(newUser);
			userRepository.save(userDB);
			return new ResponseEntity<UserEntity>(userDB, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Not appropriate user data for user type : " + e.getMessage()),
					HttpStatus.NOT_ACCEPTABLE);
		}catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/************************************************************************
	 * 							PUT gradebook/user/{id}
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserEntity newUser) {

		try {
			UserEntity userDB = userRepository.findOne(id);;
			if (userDB != null) {
				if (newUser.getFirstName()!=null) {
					userDB.setFirstName(newUser.getFirstName());
				}
				if (newUser.getLastName()!=null) {
					userDB.setLastName(newUser.getLastName());
				}
				if (newUser.getEmail()!=null) {
					userDB.setEmail(newUser.getEmail());
				}
				if (newUser.getJmbg()!=null) {
					userDB.setJmbg(newUser.getJmbg());
				}
				if (newUser.getPassword()!=null) {
					userDB.setPassword(newUser.getPassword());
				}
				userDB.setActive(true);
				userRepository.save(userDB);
				return new ResponseEntity<UserEntity>(userDB, HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "User with id " + id + " not found"),
						HttpStatus.NOT_FOUND);
			} 
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Not appropriate user data for user type : " + e.getMessage()),
					HttpStatus.NOT_ACCEPTABLE);
		}catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/************************************************************************
	 					* DELETE gradebook/user/{id}
	 					* 
	 ************************************************************************/

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		UserEntity userDB = userRepository.findOne(id);
		if (userDB == null) {
			return new ResponseEntity<RESTError>(new RESTError(1, "User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		// logicko brisanje
		userDB.setActive(false);
		userRepository.save(userDB);
		return new ResponseEntity<UserEntity>(userDB, HttpStatus.OK);
	}

}
