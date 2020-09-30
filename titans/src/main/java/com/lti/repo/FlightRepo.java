package com.lti.repo;

import java.util.List;

import com.lti.entity.Flight;

public interface FlightRepo {

	void save(Flight flight);

	Flight fetch(int flightId);

	List<Flight> fetchAll();
}
