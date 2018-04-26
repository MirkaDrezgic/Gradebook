package com.iktpreobuka.gradebook.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.gradebook.entities.TeacherEntity;

public interface TeacherRepository extends PagingAndSortingRepository<TeacherEntity, Integer> {

}
