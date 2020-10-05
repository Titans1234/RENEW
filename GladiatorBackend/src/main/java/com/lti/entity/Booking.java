package com.lti.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name = "seq_ticketid", initialValue = 1010100, allocationSize = 1)
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticketid")
	private int bookingId;

	@Column
	private int noOfSeatsBooked;

	@Column
	private LocalDate dateOfJourney;

	@Column
	private LocalDate dateOfBooking;

	@Column
	private String fromCity;

	@Column
	private String toCity;
	
	@Column
	private boolean status=false ;

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

	@Column
	private double totalCost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	@JsonIgnore
	private Flight flight;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seat> seats;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Passenger> passenger;

	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
	private Transaction transaction;

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}

	public void setNoOfSeatsBooked(int noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Ticket [bookingId=" + bookingId + ", noOfSeatsBooked=" + noOfSeatsBooked + ", dateOfJourney="
				+ dateOfJourney + ", dateOfBooking=" + dateOfBooking + ", totalCost=" + totalCost +" , transaction="+ transaction + " , status="+status+"]";
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
