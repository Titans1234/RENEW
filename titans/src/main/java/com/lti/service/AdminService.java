package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Flight;

public interface AdminService {
	void persist(Admin admin);

	Admin find(int adminid);

	List<Admin> load();

	void AdminAddFlights(Flight flight);

	void AdminRemoveFlights(int flightId);

	List<Flight> AdminViewFlights();

	Flight AdminSearchFlight(int flightid);

}