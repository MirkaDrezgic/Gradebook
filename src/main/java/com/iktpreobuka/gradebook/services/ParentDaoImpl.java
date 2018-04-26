package com.iktpreobuka.gradebook.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
@Service
public class ParentDaoImpl implements ParentDao {
	@PersistenceContext
	EntityManager em;

	@Override
	public Long countStudentsForParent(Integer idParent) {
		String sql="select count(s) from Student s left join s.parents p where p.student_id=:idParent";
		Query query=em.createQuery(sql);
		query.setParameter("idParent", idParent);
		Long result=(Long) query.getSingleResult();
		return result;
	}
}
