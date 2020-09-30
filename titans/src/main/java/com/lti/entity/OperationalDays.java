package com.lti.entity;

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

import org.hibernate.annotations.ManyToAny;

@Entity
@SequenceGenerator(name = "seq_operational", initialValue = 1010100, allocationSize = 1)
public class OperationalDays {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operational")
	private int operationalId;

	@Column
	private String operationalDays;

	@ManyToOne
	@JoinColumn(name = "flightid")
	private Flight flight;

	public int getOperationalId() {
		return operationalId;
	}

	public void setOperationalId(int operationalId) {
		this.operationalId = operationalId;
	}

	public String getOperationalDays() {
		return operationalDays;
	}

	public void setOperationalDays(String operationalDays) {
		this.operationalDays = operationalDays;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "OperationalDays [operationalId=" + operationalId + ", operationalDays=" + operationalDays + "]";
	}

}
