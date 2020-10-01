package com.lti.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Flight;
import com.lti.entity.Seat;

@Repository
public class SeatRepoImpl implements SeatRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Seat seat) {
		em.persist(seat);
	}

	@Override
	public Seat Fetch(int seatid) {

		return null;
	}

	@Override
	public void delete(int seatid) {

	}

	public List<Integer> totalSeatsBooked(List<Flight> Flight, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		List<Integer> seatsAvailable = new ArrayList<>();
		for (int i = 0; i < Flight.size(); i++) {

			Long tempseats = (Long) em.createQuery(
					"select count(s.seatId) from Seats s where (s.Flight.flightId= :flightId) AND (s.dateOfJourney=:dateOfJourney)")
					.setParameter("flightId", Flight.get(i).getFlightId()).setParameter("dateOfJourney", dateOfJourney)
					.getSingleResult();
			System.out.println(tempseats);
			seatsAvailable.add((int) (Flight.get(i).getTotalSeat() - tempseats));
			System.out.println((int) (Flight.get(i).getTotalSeat() - tempseats));
		}
		return seatsAvailable;
	}

}