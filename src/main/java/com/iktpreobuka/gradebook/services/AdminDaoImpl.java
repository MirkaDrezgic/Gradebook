package com.iktpreobuka.gradebook.services;




import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Service;




@Service
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	EntityManager em;
	
//	com.iktpreobuka.gradebook.entities.type.UserRole admin=UserRole.ADMIN_ROLE;
//	
	public boolean existEmail(String email){
//		String sql = " SELECT u " +
//				" FROM user u " + 
//		        " WHERE u.email= :email and u.user_type=: admin";
//		Query query = em.createQuery(sql);
//		UserEntity user = (UserEntity) query.getSingleResult();
//		return (user!=null);
			return true;
	};

}
