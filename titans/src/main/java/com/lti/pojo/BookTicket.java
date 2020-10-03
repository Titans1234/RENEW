package com.lti.pojo;

import java.util.List;

import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.UserDetails;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.TicketDetails;

public class BookTicket {

	private UserDetails customerDetails;

	private TicketDetails ticketDetails;

	private List<PassengerDetails> passengerDetails;

	private List<BookingSeatDetails> seatDetails;

	public UserDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(UserDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public TicketDetails getTicketDetails() {
		return ticketDetails;
	}

	public void setTicketDetails(TicketDetails ticketDetails) {
		this.ticketDetails = ticketDetails;
	}

	public List<PassengerDetails> getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

	public List<BookingSeatDetails> getSeatDetails() {
		return seatDetails;
	}

	public void setSeatDetails(List<BookingSeatDetails> seatDetails) {
		this.seatDetails = seatDetails;
	}

}
