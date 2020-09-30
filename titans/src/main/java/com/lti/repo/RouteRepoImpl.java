package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Route;

@Repository
public class RouteRepoImpl implements RouteRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Route route) {
		em.persist(route);
	}

	@Override
	public Route fetch(int routeid) {
		return em.find(Route.class, routeid);
	}

	@Override
	public List<Route> route() {
		return em.createQuery("FROM Route").getResultList();
	}

}
