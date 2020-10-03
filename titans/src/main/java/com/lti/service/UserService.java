package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import com.lti.bridge.FlightDetails;
import com.lti.entity.User;
import com.lti.pojo.UserLogin;

public interface UserService {
	// -----------UserDashboard
	void testAdd(User user);

	public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney);

	SeatCountDetails fetchNoOfSeats(int flightId, LocalDate dateOfJourney);

	// ---------Booking----------//

}
