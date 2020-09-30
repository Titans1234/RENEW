package com.lti.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {

	@Id
	@SequenceGenerator(name = "seq_seats", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seats")
	private int seatId;

	@Column
	private LocalDate dateOfJourney;

	@Column
	private int seats;

	@Column
	private String gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id")
	private Booking ticket;

	@Override
	public String toString() {
		return "Seats [seatId=" + seatId + ", dateOfJourney=" + dateOfJourney + ", seats=" + seats + ", gender="
				+ gender + ", ticket=" + ticket + ", plane=" + plane + "]";
	}

	@ManyToOne
	@JoinColumn(name = "planeid")
	private Flight plane;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public LocalDate getDate() {
		return dateOfJourney;
	}

	public void setDate(LocalDate date) {
		this.dateOfJourney = date;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Booking getTicket() {
		return ticket;
	}

	public void setTicket(Booking ticket) {
		this.ticket = ticket;
	}

	public Flight getPlane() {
		return plane;
	}

	public void setPlane(Flight plane) {
		this.plane = plane;
	}

}
