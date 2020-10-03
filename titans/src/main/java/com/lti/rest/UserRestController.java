package com.lti.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.Status;
import com.lti.pojo.SeatCount;
import com.lti.entity.User;
import com.lti.pojo.BookTicket;
import com.lti.pojo.SearchFlight;
import com.lti.service.UserServiceImpl;

@RestController
@CrossOrigin
public class UserRestController {

	@Autowired
	private UserServiceImpl ecoServ;

	@PostMapping("/searchFlight")
	public List<FlightDetails> searchAFlight(@RequestBody SearchFlight searchFlight) {
		System.out.println(searchFlight.getDateOfJourney() + "" + "give date");
		LocalDate journeyDate = LocalDate.parse(searchFlight.getDateOfJourney());
		return ecoServ.searchAFlight(searchFlight.getFromCity(), searchFlight.getToCity(), searchFlight.getDay(),
				journeyDate);

	}

	@PostMapping("/bookTicket")
	public Status addTicketDetails(@RequestBody BookTicket bookTicket) {
		// LocalDate journeyDate =
		// LocalDate.parse(bookTicket.getTicketDetails().getDateOfJourney());
		return ecoServ.addTicketDetails(bookTicket.getCustomerDetails(), bookTicket.getTicketDetails(),
				bookTicket.getPassengerDetails(), bookTicket.getSeatDetails());
	}

	@PostMapping("/addUser")
	public void addUser(@RequestBody User user) {
		ecoServ.testAdd(user);
	}

	@PostMapping("/getNoOfSeats")
	public SeatCountDetails fetchNoOfSeats(@RequestBody SeatCount seatCount) {
		System.out.println(seatCount.getDateOfJourney() + "give date");
		LocalDate journeyDate = LocalDate.parse(seatCount.getDateOfJourney());
		System.out.println(ecoServ.fetchNoOfSeats(seatCount.getFlightId(), journeyDate));
		return ecoServ.fetchNoOfSeats(seatCount.getFlightId(), journeyDate);

	}
}
