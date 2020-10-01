package com.lti.service;

import com.lti.bridge.ShowFlightDetails;
import com.lti.bridge.Status;
import com.lti.entity.Admin;
import com.lti.pojo.AdminLogin;

public interface AdminService {

	void persist(Admin admin);

	public String adminLoginService(AdminLogin ald);

	public Status removeFlight(int fightId);
	
	public ShowFlightDetails showFlight();
	
	public Status showFlight(int fightId);

}