package com.iktpreobuka.gradebook.repositories;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.gradebook.entities.AdminEntity;



public interface AdminRepository extends PagingAndSortingRepository<AdminEntity, Integer> {
	
     
	}
