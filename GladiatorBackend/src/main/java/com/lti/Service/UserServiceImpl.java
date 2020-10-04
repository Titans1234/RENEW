package com.lti.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bridge.FlightDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.email.Email;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;
import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.Login;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.TicketDetails;
import com.lti.pojo.UserDetails;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	UserRepo userepo;
	
	@Autowired
	Email email;
	
	@Override
	public RegisterStatus registerUser(User user) {
		RegisterStatus status = new RegisterStatus();
		boolean valid=userepo.checkRegisteredUser(user.getEmail());
		if (valid) {
			
			status.setMessage("This email is already registered with us!");
			return status;
		} 
	    String originalInput =user.getPassword();
	    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
	    user.setPassword(encodedString);
		userepo.registerUser(user);
		status.setMessage("You have registred successfully");
		return status;
	}

	@Override
	public LoginStatus validate(Login login) {
		String password=Base64.getEncoder().encodeToString(login.getPassword().getBytes());
		System.out.println(password);
		return userepo.loginUser(login.getUsername(), password);
	}


	public boolean isValidEmailId(String email) {
		return userepo.isValidEmail(email);
	}

	

	@Override
	public String changed(int userId, String password) {
		// TODO Auto-generated method stub
		String pass=Base64.getEncoder().encodeToString(password.getBytes());
		if(userepo.changePassword(userId, pass))
			{
			   return "Password has changed Successfully:";
			}
		return "You have entered wrong userId";
	}
	
//  Booking Services
	
	public StatusString cancelTicket(int ticketId, String email) {

		int id = 0;
		StatusString cancelTicketDetails = new StatusString();
		if (!userepo.isValidEmail(email)) {
			cancelTicketDetails.setStatus("Please enter correct email id");
			return cancelTicketDetails;
		} else if (userepo.checkRegisteredUser(email)) {
			id = userepo.getRegisteredUserId(email);
			if (userepo.isValidTicket(ticketId, id) && userepo.isValidTicketDate(ticketId)) {
                
				//Logic for cancelation 
				
				cancelTicketDetails.setStatus("B");
				return cancelTicketDetails;
			} else if (!userepo.isValidTicket(ticketId, id)) {
				cancelTicketDetails.setStatus("Cannot Fetch Ticket details at the moment.");
				return cancelTicketDetails;

			} else {
				cancelTicketDetails
						.setStatus("This ticket can not be cancelled as the date of journey has already passed.");
				return cancelTicketDetails;
			}
		}
		cancelTicketDetails.setStatus("Please Register And Login to Cancel the Ticket.");
		return cancelTicketDetails;

	}


	public Status addTicketDetails(UserDetails userDetails, TicketDetails ticketDetails,List<PassengerDetails> passengerDetails, List<BookingSeatDetails> seatDetails) {
		
		Booking ticket = new Booking();
		
		User customer = new User();
		
		Status status = new Status();//pojo
		
		Flight flight=new Flight();
		
		Transaction transaction = new Transaction();
		
		 int custId = 0;
		 
		 customer.setEmail(userDetails.getEmail());
		 custId = userepo.returnUsertId(customer);//logic conflict//userID
		
		
		List<Passenger> passenger = new ArrayList<Passenger>();
		
		List<Seat> seats = new ArrayList<Seat>();
		
		List<Booking> ticketList=new ArrayList<Booking>();

		flight.setFlightId(ticketDetails.getFlightId());
		
		for (PassengerDetails p : passengerDetails) {
			Passenger ptemp = new Passenger();
			ptemp.setAge(p.getAge());
			ptemp.setGender(p.getGender());
			ptemp.setName(p.getName());
			passenger.add(ptemp);

		}
		for (int i = 0; i < seatDetails.size(); i++) {
			Seat stemp = new Seat();
			stemp.setFlight(flight);
			stemp.setDate(ticketDetails.getDateOfJourney());
			stemp.setGender(passengerDetails.get(i).getGender());
			stemp.setSeats(seatDetails.get(i).getSeatNo());
			seats.add(stemp);
		}
		
		transaction.setAmount(ticketDetails.getTotalCost());
		transaction.setTransactionDate(ticketDetails.getDateOfBooking());
		
		customer.setCustomerId(custId);
		
		ticket.setUser(customer);
		
        ticket.setFlight(flight);
        
		ticket.setDateOfBooking(ticketDetails.getDateOfBooking());
		ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
		ticket.setNoOfSeatsBooked(ticketDetails.getNoOfSeatsBooked());
		ticket.setTotalCost(ticketDetails.getTotalCost());
		ticket.setFromCity(ticketDetails.getFromCity());
		ticket.setToCity(ticketDetails.getToCity());
      //  ticketList.add(ticket);
		boolean ticketId = userepo.addTicketAndPassengerWithRegisteredCustomers(ticket, passenger, seats, transaction);
		if (ticketId ) {

			status.setResultStatus(true);
			return status;

		}
		status.setResultStatus(false);
		return status;
	}
	
	public List<FlightDetails> searchAFlight(String fromCity, String toCity, String day, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		User cust = new User();
	Status status = new Status();
	Flight flight = new Flight();
		List<Flight> flightDetails = userepo.searchAFlight(fromCity, toCity, day);
//		List<Integer> flightId = new ArrayList<>();
//
//		for (int i = 0; i < flightDetails.size(); i++) {
//
//			flightId.add(flightDetails.get(i).getFlightId());
//		}
//		List<Flight> routesDetails = ecoRep2.searchRoutesByFlight(flightId, fromCity, toCity);

		List<Integer> totalSeat = userepo.totalSeatsBooked(flightDetails, dateOfJourney);

		List<FlightDetails> finalFlightDetails = new ArrayList<>();

		for (int i = 0; i < flightDetails.size(); i++) {
			FlightDetails f = new FlightDetails();
			f.setResultStatus(true);
			f.setFlightId(flightDetails.get(i).getFlightId());
			f.setFlightName(flightDetails.get(i).getFlightName());
			f.setDepartureTime(flightDetails.get(i).getDepartureTime());
			f.setArrivalTime(flightDetails.get(i).getArrivalTime());
			f.setFare(flightDetails.get(i).getFare());
			f.setTotalSeatsAvailable(totalSeat.get(i));
			f.setDuration(flightDetails.get(i).getDuration());
			f.setTotalSeats(flightDetails.get(i).getTotalSeat());
			finalFlightDetails.add(f);
			System.out.println(finalFlightDetails);
		}
		// return ecoRep.searchAFlight(fromCity, toCity, day);
		return finalFlightDetails;
	}
}	  

