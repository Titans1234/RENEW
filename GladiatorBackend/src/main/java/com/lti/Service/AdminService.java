package com.lti.Service;

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

	public Status FlightStatus(int fightId);

	public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId);

	public boolean addAFlight(Flight flight);

}
