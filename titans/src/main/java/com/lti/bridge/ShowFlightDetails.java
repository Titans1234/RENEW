package com.lti.bridge;

import java.util.ArrayList;
import java.util.List;

import com.lti.entity.Flight;

public class ShowFlightDetails {
	private List<Flight> flightdetails = new ArrayList<>();

	public List<Flight> getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(List<Flight> flightdetails) {
		this.flightdetails = flightdetails;
	}
}
