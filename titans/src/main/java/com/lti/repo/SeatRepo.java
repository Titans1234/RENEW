package com.lti.repo;

import java.util.List;

import com.lti.entity.Seat;

public interface SeatRepo {

	void save(Seat seat);

	Seat Fetch(int seatid);

	void delete(int seatid);

}