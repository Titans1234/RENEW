package com.lti.repo;

import java.time.LocalDate;
import java.util.List;

import com.lti.bridge.LoginStatus;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;

public interface UserRepo {
//Searching For Flight
	public List<Flight> searchAFlight(String fromCity, String toCity, String day);			
//User Needs
		
//registration methods
	    void registerUser(User user);
	    boolean checkRegisteredUser(String email);
		  
//login validation method
		   
		 public LoginStatus loginUser(String email,String password);
		   
//Forgot Password Functionalites
		   
		 boolean isValidEmail(String email);
		    
		 boolean changePassword(int customerId,String password);
			
		 int getRegisteredUserId(String email);
			
		 public User isValidUserId(int userId);
		
			      
//Other
	
	 
	 List<Booking>  fetchBookingsOfCustomer(int userId);
		   
     public User findByEmailPassword(String email,String password);
		   
	
	 Booking searchTicket(int ticketId,String email);

		
	 boolean cancelTicket(int ticketId,String email);
	 public boolean isValidTicket(int ticketId, int customerId);
	 public boolean isValidTicketDate(int ticketId);
	 public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger>passenger,List<Seat> seats,Transaction transaction);
	 
	 public List<Flight> searchRoutesByFlight(List<Integer> flightId, String fromCity, String toCity); 
	 public List<Integer> totalSeatsBooked(List<Flight> flight, LocalDate dateOfJourney);
}
