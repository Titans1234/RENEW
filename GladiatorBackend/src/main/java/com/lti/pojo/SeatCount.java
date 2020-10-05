package com.lti.pojo;

import java.time.LocalDate;

public class SeatCount {
private int flightId;
//private String dateOfJourney;
private LocalDate dateOfJourney;
public int getFlightId() {
	return flightId;
}
public void setFlightId(int flightId) {
	this.flightId = flightId;
}
//
//public String getDateOfJourney() {
//	return dateOfJourney;
//}
//public void setDateOfJourney(String dateOfJourney) {
//	this.dateOfJourney = dateOfJourney;
//}
public LocalDate getDateOfJourney() {
	return dateOfJourney;
}
public void setDateOfJourney(LocalDate dateOfJourney) {
	this.dateOfJourney = dateOfJourney;
}



}
