package com.iktpreobuka.gradebook.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.gradebook.entities.UserEntity;


public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer>{
	
		//Iterable<T> findAll(Sort sort);
		Page<UserEntity> findAll(Pageable pageable);
		//Page<UserEntity> findByNameLike(String name,Pageable pagable);
		
	

	
}
