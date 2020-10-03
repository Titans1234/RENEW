package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.SeatCountDetails;
import com.lti.bridge.Status;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;
import com.lti.entity.Seat;
import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.TicketDetails;
import com.lti.pojo.UserDetails;
import com.lti.repo.BookingRepo;
import com.lti.repo.FlightRepo;
import com.lti.repo.SeatRepo;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo ecoRep1;

	@Autowired
	private FlightRepo ecoRep2;

	@Autowired
	private SeatRepo ecoRep3;

	@Autowired
	private BookingRepo repo4;

	User cust = new User();
	Status status = new Status();
	Flight flight = new Flight();

	// ==================SEARCH FLIGHT ==========================

	public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		List<Flight> flightDetails = ecoRep2.searchAFlight(fromCity, toCity, day);
//		List<Integer> flightId = new ArrayList<>();
//
//		for (int i = 0; i < flightDetails.size(); i++) {
//
//			flightId.add(flightDetails.get(i).getFlightId());
//		}
//		List<Flight> routesDetails = ecoRep2.searchRoutesByFlight(flightId, fromCity, toCity);

		List<Integer> totalSeat = ecoRep3.totalSeatsBooked(flightDetails, dateOfJourney);

		List<FlightDetails> finalFlightDetails = new ArrayList<>();

		for (int i = 0; i < flightDetails.size(); i++) {
			FlightDetails f = new FlightDetails();
			f.setResultStatus(true);
			f.setFlightId(flightDetails.get(i).getFlightId());
			f.setFlightName(flightDetails.get(i).getFlightName());
			f.setDepartureTime(flightDetails.get(i).getDepartureTime());
			f.setArrivalTime(flightDetails.get(i).getArrivalTime());
			f.setFare(flightDetails.get(i).getFare());
			f.setTotalSeatsAvailable(totalSeat.get(i));
			f.setDuration(flightDetails.get(i).getDuration());
			f.setTotalSeats(flightDetails.get(i).getTotalSeat());
			finalFlightDetails.add(f);
			System.out.println(finalFlightDetails);
		}
		// return ecoRep.searchAFlight(fromCity, toCity, day);
		return finalFlightDetails;
	}

	// =====================BOOKING TICKET========================

	Booking ticket = new Booking();
	User customer = new User();
	Transaction transaction = new Transaction();

	public Status addTicketDetails(UserDetails userDetails, TicketDetails ticketDetails,
			List<PassengerDetails> passengerDetails, List<BookingSeatDetails> seatDetails) {
		int custId = 0;
		System.out.println(userDetails.getEmail());
		if (!ecoRep1.isValidEmail(userDetails.getEmail())) {
			cust.setEmail(userDetails.getEmail());
			custId = ecoRep1.registerUser(customer);
		} else {
			System.out.println("Please Register First!!");
		}
		List<Passenger> passenger = new ArrayList<Passenger>();
		List<Seat> seats = new ArrayList<Seat>();

		flight.setFlightId(ticketDetails.getFlightId());
		for (PassengerDetails p : passengerDetails) {
			Passenger ptemp = new Passenger();
			ptemp.setAge(p.getAge());
			ptemp.setGender(p.getGender());
			ptemp.setName(p.getName());
			passenger.add(ptemp);

		}
		for (int i = 0; i < seatDetails.size(); i++) {
			Seat stemp = new Seat();
			stemp.setFlight(flight);
			stemp.setDate(ticketDetails.getDateOfJourney());
			stemp.setGender(passengerDetails.get(i).getGender());
			stemp.setSeats(seatDetails.get(i).getSeatNo());
			seats.add(stemp);
		}
		transaction.setAmount(ticketDetails.getTotalCost());
		transaction.setTransactionDate(ticketDetails.getDateOfBooking());
		customer.setCustomerId(custId);
		ticket.setCustomer(customer);

		ticket.setFlight(flight);

		ticket.setDateOfBooking(ticketDetails.getDateOfBooking());
		ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
		ticket.setNoOfSeatsBooked(ticketDetails.getNoOfSeatsBooked());
		ticket.setTotalCost(ticketDetails.getTotalCost());
		ticket.setFromCity(ticketDetails.getFromCity());
		ticket.setToCity(ticketDetails.getToCity());

		int ticketId = ecoRep1.addTicketAndPassengerWithRegisteredCustomers(ticket, passenger, seats, transaction);
		if (ticketId > 0) {

			status.setResultStatus(true);
			return status;

		}
		status.setResultStatus(false);
		return status;
	}

	// =======Add======//
	@Transactional
	public void testAdd(User user) {
		ecoRep1.Add(user);

	}
	
	//======SeatAdd=====//
	@Override
	public SeatCountDetails fetchNoOfSeats(int flightId, LocalDate dateOfJourney) {
		List<Integer> noOfSeats = new ArrayList<>();
		noOfSeats = ecoRep.fetchNoOfSeats(busId, dateOfJourney);
		SeatCountDetails seatCountDetails = new SeatCountDetails();
		seatCountDetails.setNoOfseats(noOfSeats);
		System.out.println(noOfSeats.toString());
		return seatCountDetails;
	}

}
