package com.lti.service;

import java.util.List;

import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.pojo.AdminLogin;

public interface AdminService {

	void persist(Admin admin);

	public String adminLoginService(AdminLogin ald);

	public Status removeFlight(int fightId);

	public ShowFlightDetails showFlight();

	public Status showFlightStatus(int fightId);

	public boolean addAFlight(Flight flight);

	public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId);

	}