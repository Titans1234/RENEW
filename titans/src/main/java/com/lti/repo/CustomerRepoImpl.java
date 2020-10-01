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
public class CustomerRepoImpl implements CustomerRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void Add(User cust) {
		em.persist(cust);
	}

	@Override
	public List<User> fetchAll() {
		return em.createQuery("from User").getResultList();
	}

	@Override
	public User Fetch(int custid) {
		User emp = em.find(User.class, custid);
		return emp;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(int custid) {
		User c1 = em.find(User.class, custid);
		em.remove(c1);

	}

}
