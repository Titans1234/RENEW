package com.lti.repo;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.pojo.AdminLogin;

public interface AdminRepo {

	void save(Admin Admin);

	public boolean removeFlight(int flightId);

	public String login(AdminLogin ald);

	public List<Flight> showFlight();

	public boolean activateFlight(int flightId);

}