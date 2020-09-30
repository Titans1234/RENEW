package com.lti.repo;

import java.util.List;

import com.lti.entity.Passenger;

public interface PassengerRepo {

	void save(Passenger pass);

	Passenger fetch(int passengerId);

	List<Passenger> fetchAll();
}