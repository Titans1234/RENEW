package com.lti.Controller;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.Service.UserService;
import com.lti.bridge.FlightDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.MyBookingDetails;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.SeatCountDetails;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.bridge.ViewProfile;
import com.lti.bridge.WalletDetails;
import com.lti.pojo.ProfileCard;
import com.lti.pojo.UpdateWallet;
import com.lti.entity.User;
import com.lti.pojo.BookTicket;
import com.lti.pojo.Login;
import com.lti.pojo.MyBookings;
import com.lti.pojo.SearchFlight;
import com.lti.pojo.SeatCount;
import com.lti.pojo.WalletAmount;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserService userServ;

	@PostMapping("/register")
	public RegisterStatus registerUser(@RequestBody User user) {

		return userServ.registerUser(user);
	}

	@PostMapping(value = "/login", produces = "application/json")
//	public LoginStatus login(@RequestParam("email") String email, @RequestParam("password") String password) 
	public LoginStatus login(@RequestBody Login login){
//		login.setEmail(email);
//		login.setPassword(password);
		LoginStatus status = userServ.validate(login);
		// System.out.println(user.getUsername() + "\t" + user.getPassword());
		return status;
	}

	@PostMapping("/searchFlight")
	public List<FlightDetails> searchAFlight(@RequestBody SearchFlight searchFlight) {
		System.out.println(searchFlight.getDateOfJourney() + "" + "give date");
//		LocalDate journeyDate = LocalDate.parse(searchFlight.getDateOfJourney());// parse("year-mm-dd")
		return userServ.searchAFlight(searchFlight.getFromCity(), searchFlight.getToCity(), searchFlight.getDay(),
				searchFlight.getDateOfJourney());
	}

	@PostMapping("/bookTicket")
	public Status addTicketDetails(@RequestBody BookTicket bookTicket) {
		// LocalDate journeyDate =
		// LocalDate.parse(bookTicket.getTicketDetails().getDateOfJourney());
		System.out.println("reached till controlla");
		System.out.println(bookTicket.getCustomerDetails().getUserId());
		System.out.println(bookTicket);
		System.out.println(bookTicket.getCustomerDetails().getUserId());
		return userServ.addTicketDetails(bookTicket.getCustomerDetails(), bookTicket.getTicketDetails(),
				bookTicket.getPassengerDetails(), bookTicket.getSeatDetails());
		
	}

	@PostMapping("/cancelTicket")
	public StatusString cancelTicket(@RequestBody com.lti.pojo.CancelTicket cancelTicket) {

		return userServ.cancelTicket(cancelTicket.getBookingId(), cancelTicket.getUserId());
	}

	@PostMapping("/myBookings")
	public MyBookingDetails fetchBookingsOfCustomer(@RequestBody MyBookings myBookings) {
		
		return userServ.fetchBookingsOfCustomer(myBookings.getUserId());
	}

	@PostMapping("/getNoOfSeats")
	public SeatCountDetails fetchNoOfSeats(@RequestBody SeatCount seatCount) {
		System.out.println(seatCount.getDateOfJourney() + "give date");
	 LocalDate journeyDate = LocalDate.parse(seatCount.getDateOfJourney());
//		System.out.println(userServ.fetchNoOfSeats(seatCount.getFlightId(), journeyDate));
//		return userServ.fetchNoOfSeats(seatCount.getFlightId(), seatCount.getDateOfJourney());
	return userServ.fetchNoOfSeats(seatCount.getFlightId(), journeyDate);
	}

	@PostMapping("/walletBalance")
	public WalletDetails showWalletBalance(@RequestBody WalletAmount walletAmount) {
		return userServ.showWalletBalance(walletAmount.getUserId());
	}

	@PostMapping("/addBalance")
	public WalletDetails addWalletBalanace(@RequestBody UpdateWallet updateWallet) {

		return userServ.addWalletBalance(updateWallet.getUserId(), updateWallet.getWalletAmount());
	}
	
	
	@PostMapping("/viewProfile")
	public ViewProfile showProfile(@RequestBody ProfileCard profileCard)
	{
		return userServ.showProfile(profileCard.getUserId());
	}

}
