package com.iktpreobuka.gradebook.repositories;

import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.gradebook.entities.StudentEntity;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

//	List<StudentEntity> findByJmbg(String jmbg,Pageable pageNum);
//	Page<StudentEntity> findAll(Integer pageNum);
	//Page<UserEntity> findByNameLike(String name,Pageable pagable);
	List<StudentEntity> findByJmbg(String jmbg);
	List<StudentEntity> findAll();
	
}
