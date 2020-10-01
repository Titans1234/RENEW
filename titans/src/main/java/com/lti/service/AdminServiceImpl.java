package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.pojo.AdminLogin;
import com.lti.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	Status status = new Status();
	Flight flight = new Flight();

	@Autowired
	private AdminRepo ecoRep;

	@Transactional(value = TxType.REQUIRED)
	public void persist(Admin admin) {
		ecoRep.save(admin);
	}

	@Override
	public String adminLoginService(AdminLogin ald) {
		return ecoRep.login(ald);
	}
	/*
	 * @Override public String cancelFlightAndUpdateInCustomerBookingService(int
	 * flightId) { ecoRep.AdminRemoveFlights(flightId); return
	 * "Flight deleted successfully and refund initiated"; }
	 */

	@Override
	public Status removeFlight(int flightId) {
		if (ecoRep.removeFlight(flightId)) {
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

	@Override
	public ShowFlightDetails showFlight() {
		ShowFlightDetails showFlightDetails = new ShowFlightDetails();
		List<Flight> flight = new ArrayList<>();
		flight = ecoRep.showFlight();
		showFlightDetails.setFlightdetails(flight);
		return showFlightDetails;
	}
	
	@Override
	public Status showFlight(int flightId) {
		if (ecoRep.activateFlight(flightId)) {
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

}