package com.lti.Controller;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

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
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.entity.User;
import com.lti.pojo.BookTicket;
import com.lti.pojo.Login;
import com.lti.pojo.SearchFlight;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  private UserService userServ;
  
  @PostMapping("/register")
  public RegisterStatus registerUser(@RequestBody User user) {
	   
		return userServ.registerUser(user);
	}
  
  @GetMapping(value = "/login", produces = "application/json")
	public LoginStatus login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		LoginStatus status=userServ.validate(login);
		//System.out.println(user.getUsername() + "\t" + user.getPassword());
		return status;
}
  @PostMapping("/searchFlight")
	public List<FlightDetails> searchAFlight(@RequestBody SearchFlight searchFlight) {
		System.out.println(searchFlight.getDateOfJourney() + "" + "give date");
		LocalDate journeyDate = LocalDate.parse(searchFlight.getDateOfJourney());//parse("year-mm-dd")
		return userServ.searchAFlight(searchFlight.getFromCity(), searchFlight.getToCity(), searchFlight.getDay(),journeyDate);

	}

	@PostMapping("/bookTicket")
	public Status addTicketDetails(@RequestBody BookTicket bookTicket) {
		// LocalDate journeyDate =
		// LocalDate.parse(bookTicket.getTicketDetails().getDateOfJourney());
		return userServ.addTicketDetails(bookTicket.getCustomerDetails(), bookTicket.getTicketDetails(),
				bookTicket.getPassengerDetails(), bookTicket.getSeatDetails());
	}
  @PostMapping("/cancelTicket")
	public StatusString cancelTicket(@RequestBody com.lti.pojo.CancelTicket cancelTicket) {
		
		return userServ.cancelTicket(cancelTicket.getTicketno(), cancelTicket.getEmail());
	}
  
  

}
