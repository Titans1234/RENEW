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
import com.lti.bridge.AdminLoginStatus;
import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.pojo.AdminLogin;
import com.lti.pojo.RemoveFlight;
//(origins = "http://localhost:4200")
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

	@PostMapping(value = "/loginAdmin", produces = "application/json")
//	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
	public AdminLoginStatus adminlogin(@RequestBody AdminLogin adminLogin){
   
		AdminLoginStatus status=ecoServ.adminLoginService(adminLogin);
		    return status ;
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
	

	
	@PostMapping("/removeFlight")
//	public Status removeFlight(@RequestBody RemoveFlight removeFlight) {
	public Status removeFlight(@RequestParam("flightId") int flightId){
//		System.out.println("FlightId" + removeFlight.getFlightId());
		System.out.println("FlightId"+flightId);
		return ecoServ.removeFlight(flightId);
	}

	@PostMapping(path = "/showFlight")
	public ShowFlightDetails showFlight(@RequestBody Boolean flag) {
       if(flag)
    	   System.out.println("Ab chal ja");
    	   return ecoServ.showFlight();
	}
	
	@GetMapping(path = "/inactiveFlight")
	public List<Flight> IncativeFlight() {		
			//System.out.println("Searching..............."+status.getStatus());
	   System.out.println("reached");
		List<Flight> flight=ecoServ.inactiveFlight();
		for(Flight f:flight)
		{
			System.out.println(f.getFlightId());
		}
		return flight;
}
	
	  @GetMapping("/activateFlight")
	  public Status activateFlight(@RequestParam("flightId") int flightId) {
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
