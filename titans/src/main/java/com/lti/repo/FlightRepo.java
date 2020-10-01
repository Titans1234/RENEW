package com.lti.repo;

import java.util.List;

import com.lti.entity.Flight;

public interface FlightRepo {

	void save(Flight flight);

	Flight fetch(int flightId);

	List<Flight> fetchAll();

	public List<Flight> searchAFlight(String fromCity, String toCity, String day);
 
	public List<Flight> searchRoutesByFlight(List<Integer> flightId, String fromCity, String toCity);
}
