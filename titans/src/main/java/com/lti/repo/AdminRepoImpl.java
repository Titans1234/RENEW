package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Admin admin) {
		em.persist(admin);

	}

	public Admin fetch(int adminId) {
		Admin f = em.find(Admin.class, adminId);
		return f;
	}

	public List<Admin> fetchAll() {
		return em.createQuery("from Admin").getResultList();
	}

}
