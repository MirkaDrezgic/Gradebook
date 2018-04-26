package com.iktpreobuka.gradebook.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.gradebook.entities.SectionEntity;
import com.iktpreobuka.gradebook.entities.type.ClassType;
import com.iktpreobuka.gradebook.entities.type.SectionType;

@Service
public class SectionDaoImpl implements SectionDao {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Long countSectionsForClass(Integer idClass) {
		String sql="select count(s) from SectionEntity s WHERE s.classEntity.id=:idClass";
		Query query=em.createQuery(sql);
		query.setParameter("idClass", idClass);
		Long result=(Long) query.getSingleResult();
		return result;
	}

	public boolean existsSectionForClass(Integer idClass, SectionType sectionName) {
		String sql="select count(s) from SectionEntity s WHERE s.classEntity.id=:idClass and s.sectionType=:sectionName";
		Query query=em.createQuery(sql);
		query.setParameter("idClass", idClass);
		query.setParameter("sectionName", sectionName);
		Long result=(Long) query.getSingleResult();
		return (result>1);
	}

	@Override
	//pronadji odeljenja ako znas oznaku razreda i godinu
	public List<SectionEntity> findAllSection(String year, ClassType labelClass) {
		String sql="SELECT s FROM SectionEntity s "
				+"LEFT JOIN FETCH s.classEntity as c "+
				"WHERE c.classType =: labelClass AND c.year =: year";
		Query query=em.createQuery(sql);
		query.setParameter("labelClass", labelClass);
		query.setParameter("year", year);
		List<SectionEntity> result=new ArrayList<>();
		query.getResultList();
		return result;
		
	}

	@Override
	//pronadji odeljenja ako znas oznaku razreda, oznaku odeljenja i skolsku godinu
	public SectionEntity findSection(ClassType labelClass, String year, SectionType labelSection) {
		String sql="SELECT s FROM SectionEntity s "+
				"	LEFT JOIN FETCH s.classEntity as c	"
				+ " WHERE c.classType = :labelClass "
				 		+ "AND c.year =: year "
				 		+" AND s.sectionType =: labelSection ";
		Query query=em.createQuery(sql);
		query.setParameter("labelClass", labelClass);
		query.setParameter("labelSection", labelSection);
		query.setParameter("year", year);
		SectionEntity result=(SectionEntity) query.getSingleResult();
		return result;
	}

	

}
