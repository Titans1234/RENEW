package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo repo;

	@Override
	public void persist(Admin admin) {
		repo.save(admin);
	}

	@Override
	public Admin find(int adminid) {
		return repo.fetch(adminid);
		
	}

	@Override
	public List<Admin> load() {
		return repo.fetchAll();
	}

	@Override
	public void AdminAddFlights(Flight flight) {
			
	}

	@Override
	public void AdminRemoveFlights(int flightId) {
		
	}

	@Override
	public List<Flight> AdminViewFlights() {
		
		return null;
	}

	@Override
	public Flight AdminSearchFlight(int flightid) {
		
		return null;
	}

}
