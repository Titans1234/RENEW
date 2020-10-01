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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@SequenceGenerator(name = "seq_flightid", initialValue = 131100, allocationSize = 1)
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flightid")
	private int flightId;

	@Column
	private int totalSeat;

	@Column
	private String flightName;

	@Column
	private String flightStatus;

	@Column
	private String fromCity;

	@Column
	private String toCity;

	@Column
	private String departureTime;

	@Column
	private String arrivalTime;

	@Column
	private double Fare;

	@Column
	private String duration;

	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<OperationalDays> operationalDays;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> booking;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	@JsonIgnore
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

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

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

	public double getFare() {
		return Fare;
	}

	public void setFare(double fare) {
		Fare = fare;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<OperationalDays> getOperationalDays() {
		return operationalDays;
	}

	public void setOperationalDays(List<OperationalDays> operationalDays) {
		this.operationalDays = operationalDays;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
