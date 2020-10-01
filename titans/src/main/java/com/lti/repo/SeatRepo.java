package com.lti.repo;

import java.time.LocalDate;
import java.util.List;

import com.lti.entity.Flight;
import com.lti.entity.Seat;

public interface SeatRepo {

	void save(Seat seat);

	Seat Fetch(int seatid);

	void delete(int seatid);

	public List<Integer> totalSeatsBooked(List<Flight> flight, LocalDate dateOfJourney);
}