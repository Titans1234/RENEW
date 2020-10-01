package com.lti.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bridge.FlightDetails;
import com.lti.pojo.SearchFlight;
import com.lti.service.UserServiceImpl;

@RestController
@CrossOrigin
public class UserRestController {

	@Autowired
	private UserServiceImpl ecoServ;

	@PostMapping("/searchFlight")
	public List<FlightDetails> searchAFlight (@RequestBody SearchFlight searchFlight){
	System.out.println(searchFlight.getDateOfJourney()+""+"give date");
	LocalDate journeyDate= LocalDate.parse(searchFlight.getDateOfJourney());
	return ecoServ.searchAFlight(searchFlight.getFromCity(),searchFlight.getToCity(),searchFlight.getDay(),journeyDate);
	
	}	
}
