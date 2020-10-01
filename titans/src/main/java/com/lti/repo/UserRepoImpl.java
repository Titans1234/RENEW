package com.lti.repo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.User;;

@Repository
public class UserRepoImpl implements UserRepo{
     
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void Add(User cust) {
		// TODO Auto-generated method stub
		em.persist(cust);
	}

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from User").getResultList();
	}

	@Override
	public User Fetch(int custid) {
		// TODO Auto-generated method stub
		User emp=em.find(User.class,custid);
		return emp;
	}

	@Transactional(value=TxType.REQUIRED)
	public void delete(int custid) {
		// TODO Auto-generated method stub
		User c1= em.find(User.class,custid);
		 em.remove(c1);
		
	}
   
}
