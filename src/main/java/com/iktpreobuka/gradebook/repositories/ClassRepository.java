package com.iktpreobuka.gradebook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.gradebook.entities.ClassEntity;
import com.iktpreobuka.gradebook.entities.type.ClassType;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {

	List<ClassEntity> findByYear(String year);

	ClassEntity findByYearAndClassType(String year, ClassType classType);

}
