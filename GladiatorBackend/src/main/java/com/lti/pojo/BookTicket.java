package com.lti.pojo;

import java.util.List;

public class BookTicket {

	private List<PassengerDetails> passengerDetails;
	
	private UserDetails customerDetails;

	private TicketDetails ticketDetails;

	

	private List<BookingSeatDetails> seatDetails;

	@Override
	public String toString() {
		return "BookTicket [customerDetails=" + customerDetails + ", ticketDetails=" + ticketDetails
				+ ", passengerDetails=" + passengerDetails + ", seatDetails=" + seatDetails + "]";
	}

	
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
