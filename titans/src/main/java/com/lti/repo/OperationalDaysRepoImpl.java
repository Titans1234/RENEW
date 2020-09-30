package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.OperationalDays;

@Repository
public class OperationalDaysRepoImpl implements OperationalDaysRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional(value = TxType.REQUIRED)
	public void save(OperationalDays operationaldays) {
		em.persist(operationaldays);

	}

	@Override
	public OperationalDays fetch(int operationalId) {

		return em.find(OperationalDays.class, operationalId);
	}

	@Override
	public List<OperationalDays> fetchAll() {

		return em.createQuery("FROM OperationalDays").getResultList();
	}

}
