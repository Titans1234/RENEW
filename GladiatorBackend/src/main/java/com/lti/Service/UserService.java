package com.lti.Service;

import java.time.LocalDate;
import java.util.List;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.entity.User;
import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.Login;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.TicketDetails;
import com.lti.pojo.UserDetails;

public interface UserService {
	RegisterStatus registerUser(User user);
	
    public LoginStatus validate(Login login);
    public boolean isValidEmailId(String email);
    public String changed(int userId,String password);
    
    public StatusString cancelTicket(int ticketId, String email);
    
    public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney);

	Status addTicketDetails(UserDetails customerDetails, TicketDetails ticketDetails,
			List<PassengerDetails> passengerDetails, List<BookingSeatDetails> seatDetails);
    
    
    
    
}
