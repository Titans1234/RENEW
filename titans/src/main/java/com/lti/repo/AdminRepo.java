package com.lti.repo;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Flight;

public interface AdminRepo {

	void save(Admin Admin);

	Admin fetch(int adminId);

	List<Admin> fetchAll();

	void AdminAddFlights(Flight flight);

	void AdminRemoveFlights(int flightId);

	List<Flight> AdminViewFlights();

	Flight AdminSearchFlight(int flightid);

	boolean validateAdmin(String username, String password);
	
	

}