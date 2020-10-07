package com.lti.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.MyBookTickets;
import com.lti.bridge.MyBookingDetails;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.SeatCountDetails;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.bridge.ViewProfile;
import com.lti.bridge.WalletDetails;
import com.lti.email.Email;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;
import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.Login;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.TicketDetails;
import com.lti.pojo.UserDetails;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userepo;

	@Autowired
	Email email;

//==========================ADD USER=========================//
	@Override
	public RegisterStatus registerUser(User user) {
		RegisterStatus status = new RegisterStatus();
		boolean valid = userepo.checkRegisteredUser(user.getEmail());
		if (valid) {

			status.setMessage("This email is already registered with us!");
			return status;
		}
		String originalInput = user.getPassword();
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		user.setPassword(encodedString);
		userepo.registerUser(user);
		status.setMessage("You have registred successfully");
		return status;
	}

//=========================VALIDATE LOGIN================//
	@Override
	public LoginStatus validate(Login login) {
		String password = Base64.getEncoder().encodeToString(login.getPassword().getBytes());
		System.out.println(password);
		return userepo.loginUser(login.getEmail(), password);
	}

	public boolean isValidEmailId(String email) {
		return userepo.isValidEmail(email);
	}

//================CHANGE PASSWORD==========================//
	@Override
	public String changed(int userId, String password) {
		// TODO Auto-generated method stub
		String pass = Base64.getEncoder().encodeToString(password.getBytes());
		if (userepo.changePassword(userId, pass)) {
			return "Password has changed Successfully:";
		}
		return "You have entered wrong userId";
	}

//==================CANCEL TICKET=========================//

	public StatusString cancelTicket(int bookingId, int userId) {

		boolean a = userepo.checkUser(userId);
		if (a) {
			
			StatusString s1 = new StatusString();
			s1= userepo.cancelTicket(bookingId);
			return s1;
		}
		StatusString s2 = new StatusString();
		s2.setStatus("Please enter valid userId");
		System.out.println(s2);
		return s2;

	}

//		int id = 0;
//		StatusString cancelTicketDetails = new StatusString();
//		if (!userepo.isValidEmail(email)) {
//			cancelTicketDetails.setStatus("Please enter correct email id");
//			return cancelTicketDetails;
//		} else if (userepo.checkRegisteredUser(email)) {
//			id = userepo.getRegisteredUserId(email);
//			if (userepo.isValidTicket(ticketId, id) && userepo.isValidTicketDate(ticketId)) {
//
//				// Logic for cancelation
//
//				cancelTicketDetails.setStatus("B");
//				return cancelTicketDetails;
//			} else if (!userepo.isValidTicket(ticketId, id)) {
//				cancelTicketDetails.setStatus("Cannot Fetch Ticket details at the moment.");
//				return cancelTicketDetails;
//
//			} else {
//				cancelTicketDetails
//						.setStatus("This ticket can not be cancelled as the date of journey has already passed.");
//				return cancelTicketDetails;
//			}
//		}
//		cancelTicketDetails.setStatus("Please Register And Login to Cancel the Ticket.");
//		return cancelTicketDetails;

//============BOOKING A TICKET===============================================================================================//

	public Status addTicketDetails(UserDetails userDetails, TicketDetails ticketDetails,
			List<PassengerDetails> passengerDetails, List<BookingSeatDetails> seatDetails) {
		Booking ticket = new Booking();
		User customer = new User();
		Status status = new Status();
		Flight flight = new Flight();
		Transaction transaction = new Transaction();
		int custId = 0;
		System.out.println(userDetails.getEmail());
//		custId = userepo.returnUsertId(customer);
		custId = userDetails.getUserId();

//		if (!userepo.isValidEmail(userDetails.getEmail())) {
//			customer.setEmail(userDetails.getEmail());
//			
//		} else {
//			System.out.println("Please Register First!!");
//		}
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
			stemp.setGender(seatDetails.get(i).getSeatClass());
			stemp.setSeats(seatDetails.get(i).getSeatNo());
			seats.add(stemp);
		}
		transaction.setAmount(ticketDetails.getTotalCost());
		transaction.setTransactionDate(ticketDetails.getDateOfBooking());
		customer.setCustomerId(custId);

		ticket.setUser(customer);

		ticket.setFlight(flight);

		ticket.setDateOfBooking(ticketDetails.getDateOfBooking());
		ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
		ticket.setNoOfSeatsBooked(ticketDetails.getNoOfSeatsBooked());
		ticket.setTotalCost(ticketDetails.getTotalCost());
		ticket.setFromCity(ticketDetails.getFromCity());
		ticket.setToCity(ticketDetails.getToCity());

		int ticketId = userepo.addTicketAndPassengerWithRegisteredCustomers(ticket, passenger, seats, transaction);
		if (ticketId > 0) {

			status.setResultStatus(true);
			return status;

		}
		status.setResultStatus(false);
		return status;
	}

// ========================SEARCH A FLIGHT===============================================================================================//

	public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		User cust = new User();
		Status status = new Status();
		Flight flight = new Flight();
		List<Flight> flightDetails = userepo.searchAFlight(fromCity, toCity, day);
//		List<Integer> flightId = new ArrayList<>();
//
//		for (int i = 0; i < flightDetails.size(); i++) {
//
//			flightId.add(flightDetails.get(i).getFlightId());
//		}
//		List<Flight> routesDetails = ecoRep2.searchRoutesByFlight(flightId, fromCity, toCity);

		List<Integer> totalSeat = userepo.totalSeatsBooked(flightDetails, dateOfJourney);

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

// =======================FETCH BOOKING RECORDS=============================================================================//
	@Override
	public MyBookingDetails fetchBookingsOfCustomer(int userId) {
		List<Booking> myBookings = userepo.fetchAllBookingsOfCustomer(userId);
		List<MyBookTickets> myBookTickets = new ArrayList<>();
		MyBookingDetails myBookingDetails = new MyBookingDetails();
		if (myBookings.size() > 0) {
			for (int i = 0; i < myBookings.size(); i++) {

				MyBookTickets mbt = new MyBookTickets();
				mbt.setToCity(myBookings.get(i).getToCity());
				mbt.setFromCity(myBookings.get(i).getFromCity());
				mbt.setDateOfBooking(myBookings.get(i).getDateOfBooking());
				mbt.setDateOfJourney(myBookings.get(i).getDateOfJourney());
				mbt.setNoOfSeatsBooked(myBookings.get(i).getNoOfSeatsBooked());
				mbt.setTotalCost(myBookings.get(i).getTotalCost());
				mbt.setTicketId(myBookings.get(i).getBookingId());
				if (myBookings.get(i).getDateOfJourney().minusDays(2).compareTo(LocalDate.now()) > 0) {
					mbt.setCancelButton(true);

				} else {
					mbt.setCancelButton(false);
				}
				myBookTickets.add(mbt);
			}
			myBookingDetails.setMybookings(myBookTickets);
			myBookingDetails.setResultStatus(true);
			return myBookingDetails;

		}
		myBookingDetails.setResultStatus(false);
		return myBookingDetails;

	}

// ========================FETCH NO OF SEATS==================================================================================//
	public SeatCountDetails fetchNoOfSeats(int flightId, LocalDate dateOfJourney) {
		List<Integer> noOfSeats = new ArrayList<>();
		noOfSeats = userepo.fetchNoOfSeats(flightId, dateOfJourney);
		SeatCountDetails seatCountDetails = new SeatCountDetails();
		seatCountDetails.setNoOfseats(noOfSeats);
		System.out.println(noOfSeats.toString());
		return seatCountDetails;

	}

//==========================WALLET========================================================================================//
	public WalletDetails showWalletBalance(int userId) {

		WalletDetails walletAmount = new WalletDetails();
		walletAmount.setAmount(userepo.showWalletBalance(userId));
		return walletAmount;

	}

	public WalletDetails addWalletBalance(int userId, double amount) {

		WalletDetails wallet = new WalletDetails();
		if (userepo.addWalletBalance(userId, amount)) {
			wallet.setStatus("Amount added successfully!");
			wallet.setAmount(userepo.showWalletBalance(userId));
		} else {
			wallet.setStatus("Oops! Could not add amount");
			wallet.setAmount(userepo.showWalletBalance(userId));
		}
		return wallet;
	}
//==================SHOW PROFILE===============================================//
	
	public ViewProfile showProfile(int userId) {
		User user=new User();
		ViewProfile viewProfile =new ViewProfile();
		user=userepo.showProfile(userId);
		viewProfile.setAge(user.getAge());
		viewProfile.setContact(user.getContact());
		viewProfile.setEmail(user.getEmail());
		viewProfile.setGender(user.getGender());
		viewProfile.setName(user.getName());
		
		return viewProfile;
	}
}
