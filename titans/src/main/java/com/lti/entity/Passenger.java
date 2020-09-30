package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_passenger", initialValue = 10100, allocationSize = 1)
@Table(name="passanger")
public class Passenger {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_passenger")
	private int passengerId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int age;
	
	@Column(nullable=false)
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private Booking ticket;
	

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	
	
}
