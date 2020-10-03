package com.lti.repo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Flight;

@Repository
public class FlightRepoImpl implements FlightRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Flight flight) {
		em.persist(flight);
	}

	public Flight fetch(int flightId) {
		Flight f = em.find(Flight.class, flightId);
		return f;
	}

	public List<Flight> fetchAll() {
		return em.createQuery("from Flight").getResultList();
	}

	@Override
	public List<Flight> searchAFlight(String fromCity, String toCity, String day) {
		String sql = "select f from Flight f where f.FlightStatus='active' AND  f.fromCity=:from AND f.toCity=:to and f.flightId in(select o.Flight.flightId from OperationalDays as o where o.operationalDays=:day))";
		TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
		query.setParameter("from", fromCity);
		query.setParameter("to", toCity);
		query.setParameter("day", day);
		List<Flight> flight = query.getResultList();
		return flight;
	}

	public List<Flight> searchRoutesByFlight(List<Integer> flightId, String fromCity, String toCity) {
		List<Flight> routeDetails = new ArrayList<>();
//		for (int i = 0; i < flightId.size(); i++) {
//
//			String sql = "select f from Flight f where (f.flightId=:flightId) AND (f.fromCity=:fromCity) AND (f.toCity=:toCity)";
//			TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
//			query.setParameter("flightId", flightId.get(i));
//			query.setParameter("fromCity", fromCity);
//			query.setParameter("toCity", toCity);
//
//			Flight routes = query.getSingleResult();
//			routeDetails.add(routes);
//		}
		return routeDetails;
	}

}