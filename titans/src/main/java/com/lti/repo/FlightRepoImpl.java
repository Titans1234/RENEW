package com.lti.repo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Flight;
import com.lti.model.Routes;


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
		String sql = "select f from Flight f where f.FlightStatus='active' AND f.FlightId in (select r.flightId from Flight r where r.fromCity=:"
				+ "from and r.toCity=:to and r.Flight.flightId in(select o.Flight.flightId from OperationalDays as o where o.operationalDays=:day))";
		TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
		query.setParameter("from", fromCity);
		query.setParameter("to", toCity);
		query.setParameter("day", day);
		List<Flight> flight = query.getResultList();
		return flight;
	}
	
	public List<Routes> searchRoutesByFlight(List<Integer> flightId, String fromCity, String toCity) {
		List<Routes> routeDetails = new ArrayList<>();
		for (int i = 0; i < flightId.size(); i++) {

			String sql = "select r from Routes r where (r.bus.busId=:busId) AND (r.fromCity=:fromCity) AND (r.toCity=:toCity)";
			TypedQuery<Routes> query = em.createQuery(sql, Routes.class);
			query.setParameter("busId", flightId.get(i));
			query.setParameter("fromCity", fromCity);
			query.setParameter("toCity", toCity);

			Routes routes = query.getSingleResult();
			routeDetails.add(routes);
		}
		return routeDetails;
	}

}