package com.lti.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.Service.AdminService;
import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.pojo.AdminLogin;
import com.lti.pojo.RemoveFlight;

@CrossOrigin
@RestController
public class AdminController {

	@Autowired
	private AdminService ecoServ;

	@PostMapping(value = "/addAdmin", consumes = "application/json")
	public String addEmployee(@RequestBody Admin admin) {
		ecoServ.persist(admin);
		return "Admin added successfully";
	}

	@GetMapping(value = "/loginAdmin", produces = "application/json")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
    
		AdminLogin login = new AdminLogin(username, password);
		return ecoServ.adminLoginService(login);
		
	}

	/*
	 * @GetMapping(path = "/cancelFlight", produces = "application/json") public
	 * String cancelFlight(@RequestParam("flightId") int flightId) { return
	 * ecoServ.cancelFlightAndUpdateInCustomerBookingService(flightId); }
	 */

	/*
	 * @GetMapping("/removeFlight") public Status
	 * removeFlight(@RequestParam("flightId") int flightId) {
	 * System.out.println("FlightId" + flightId); return
	 * ecoServ.removeFlight(flightId); }
	 */

	@GetMapping("/removeFlight")
	public Status removeFlight(@RequestBody RemoveFlight removeFlight) {
		System.out.println("FlightId" + removeFlight.getFlightId());
		return ecoServ.removeFlight(removeFlight.getFlightId());
	}

	@RequestMapping(path = "/showFlight")
	public ShowFlightDetails showFlight() {		
		return ecoServ.showFlight();
	}
	
	
	  @GetMapping("/activateFlight") public Status
	  removeFlight(@RequestParam("flightId") int flightId) {
	  System.out.println("FlightId" + flightId); 
	  return  ecoServ.FlightStatus(flightId);
	 
	  }
	    
		
	    @PostMapping("/addFlight")
		public boolean addFlight(Flight flight) {
			return ecoServ.addAFlight(flight);
		}

		@PostMapping("/addOperationalDaysWithFlight")
		public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId) {
			return ecoServ.addOperationalDaysWithFlight(operationalDays, flightId);
		}
		 
	
}
