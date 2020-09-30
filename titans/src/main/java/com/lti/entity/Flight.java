package com.lti.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_flightid", initialValue = 131100, allocationSize = 1)
@Table(name="flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flightid")
	private int flightId;

	@Column
	private int totalSeat;

	@Column
	private String flightName;

	

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<Route> routes;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<OperationalDays> operationalDays;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private List<Booking> ticket;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL) 
	private List<Seat> seats;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}


	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public List<OperationalDays> getOperationalDays() {
		return operationalDays;
	}

	public void setOperationalDays(List<OperationalDays> operationalDays) {
		this.operationalDays = operationalDays;
	}

	public List<Booking> getTicket() {
		return ticket;
	}

	public void setTicket(List<Booking> ticket) {
		this.ticket = ticket;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
