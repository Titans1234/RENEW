package com.lti.bridge;

import java.util.ArrayList;
import java.util.List;

public class MyBookingDetails extends Status {
	private List<MyBookTickets> mybookings = new ArrayList<>();

	public List<MyBookTickets> getMybookings() {
		return mybookings;
	}

	public void setMybookings(List<MyBookTickets> mybookings) {
		this.mybookings = mybookings;
	}

	@Override
	public String toString() {
		return "MyBookingDetails [mybookings=" + mybookings + "]";
	}

}
