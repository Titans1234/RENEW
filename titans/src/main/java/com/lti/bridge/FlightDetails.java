package com.lti.bridge;

public class FlightDetails extends Status{
	private int flightId;

	private String flightName;

	private String departureTime;

	private String arrivalTime;

	private String duration;

	private double fare;

	private int totalSeatsAvailable;

	private int totalSeats;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}

	public void setTotalSeatsAvailable(int totalSeatsAvailable) {
		this.totalSeatsAvailable = totalSeatsAvailable;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	@Override
	public String toString() {
		return "BusDetails [busId=" + flightId + ", flightName=" + flightName + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", duration=" + duration + ", fare=" + fare
				+ ", totalSeatsAvailable=" + totalSeatsAvailable + "]";
	}

}
