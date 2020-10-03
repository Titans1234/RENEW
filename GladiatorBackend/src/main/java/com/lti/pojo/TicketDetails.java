package com.lti.pojo;

import java.time.LocalDate;

public class TicketDetails {

		private LocalDate dateOfBooking = LocalDate.now();
		private LocalDate dateOfJourney;
		private int noOfSeatsBooked;
		private double totalCost;
		private int flightId;
		private String fromCity;
		private String toCity;

		public String getFromCity() {
			return fromCity;
		}

		public void setFromCity(String fromCity) {
			this.fromCity = fromCity;
		}

		public String getToCity() {
			return toCity;
		}

		public void setToCity(String toCity) {
			this.toCity = toCity;
		}

		public int getFlightId() {
			return flightId;
		}

		public void setFlightId(int flightId) {
			this.flightId = flightId;
		}

		public LocalDate getDateOfBooking() {
			return dateOfBooking;
		}

		public void setDateOfBooking(LocalDate dateOfBooking) {
			this.dateOfBooking = dateOfBooking;
		}

		public LocalDate getDateOfJourney() {
			return dateOfJourney;
		}

		public void setDateOfJourney(LocalDate dateOfJourney) {
			this.dateOfJourney = dateOfJourney;
		}

		public int getNoOfSeatsBooked() {
			return noOfSeatsBooked;
		}

		public void setNoOfSeatsBooked(int noOfSeatsBooked) {
			this.noOfSeatsBooked = noOfSeatsBooked;
		}

		public double getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}
}
