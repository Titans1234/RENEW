package com.lti.repo;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.entity.User;
import com.lti.pojo.AdminLogin;

public interface AdminRepo {
	void save(Admin Admin);

	public boolean removeFlight(int flightId);

	public String login(AdminLogin ald);

	public List<Flight> showFlight();
	
	public List<OperationalDays> showOperationalDays(int flightId);

	public boolean activateFlight(int flightId);
	   
    public boolean addAflight(Flight flight);
	
    public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId);
}
