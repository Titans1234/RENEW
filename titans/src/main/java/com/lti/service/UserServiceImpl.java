package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher.Route;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Flight;
import com.lti.entity.User;
import com.lti.pojo.UserLogin;
import com.lti.repo.FlightRepo;
import com.lti.repo.SeatRepo;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo ecoRep;

	@Autowired
	private SeatRepo ecoRep1;

	@Autowired
	private FlightRepo ecoRep2;

	User cust = new User();
	Status status = new Status();

	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		return ecoRep.fetchAll();
	}

	@Override
	public User fetch(int userId) {
		// TODO Auto-generated method stub
		return ecoRep.Fetch(userId);
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		ecoRep.Add(user);

	}

	@Override
	public void removeUser(int UserId) {
		// TODO Auto-generated method stub
		ecoRep.delete(UserId);

	}

	@Override
	public boolean ValidateUser(UserLogin login) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		List<Flight> flightDetails = ecoRep2.searchAFlight(fromCity, toCity, day);
		List<Integer> busId = new ArrayList<>();

		for (int i = 0; i < flightDetails.size(); i++) {

			busId.add(flightDetails.get(i).getFlightId());
		}
		List<Route> routesDetails = ecoRep.searchRoutesByFlight(flightId, fromCity, toCity);

		List<Integer> totalSeat = ecoRep1.totalSeatsBooked(flightDetails, dateOfJourney);

		List<FlightDetails> finalFlightDetails = new ArrayList<>();

		for (int i = 0; i < flightDetails.size(); i++) {
			FlightDetails b = new FlightDetails();
			b.setResultStatus(true);
			b.setFlightId(flightDetails.get(i).getFlightId());
			b.setFlightName(flightDetails.get(i).getFlightName());
			b.setDepartureTime(routesDetails.get(i).getDepartureTime());
			b.setArrivalTime(routesDetails.get(i).getArrivalTime());
			b.setFare(routesDetails.get(i).getFare());
			b.setTotalSeatsAvailable(totalSeat.get(i));
			b.setDuration(routesDetails.get(i).getDuration());
			b.setTotalSeats(flightDetails.get(i).getTotalSeat());
			finalFlightDetails.add(b);
			System.out.println(finalFlightDetails);
		}
		// return ecoRep.searchABus(fromCity, toCity, day);
		return finalFlightDetails;
	}

}
