package com.lti.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional.TxType;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.lti.bridge.AdminLoginStatus;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.pojo.AdminLogin;
import com.lti.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	Status status = new Status();
	Flight flight = new Flight();

	@Autowired
	private AdminRepo ecoRep;

//===============ADD ADMIN=============//
	public void persist(Admin admin) {
		ecoRep.save(admin);
	}

//==============ADMIN LOGIN===========//
	@Override
	public AdminLoginStatus adminLoginService(AdminLogin admin) {
			return ecoRep.login(admin);
	}

//========REMOVE FLIGHT======================//
	@Override
	public Status removeFlight(int flightId) {
		if (ecoRep.removeFlight(flightId)) {
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

//=============SHOW FLIGHT  LIST==================//
	@Override
	public ShowFlightDetails showFlight() {
		ShowFlightDetails showFlightDetails = new ShowFlightDetails();
		List<Flight> flight = new ArrayList<>();
		flight = ecoRep.showFlight();
		showFlightDetails.setFlightdetails(flight);
		return showFlightDetails;
	}

//==============TO ACTIVATE FLIGHT===========//	
	public Status FlightStatus(int flightId) {
		if (ecoRep.activateFlight(flightId)) {
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

//=================ADD FLIGHT=============//
	@Override
	public boolean addAFlight(Flight flight) {
		return ecoRep.addAflight(flight);
	}

//==============OPERATIONAL DAYS===========//	
	@Override
	public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId) {
		return ecoRep.addOperationalDaysWithFlight(operationalDays, flightId);
	}

}
