package com.lti.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.bridge.LoginStatus;
import com.lti.bridge.MyBookingDetails;
import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;

@Repository
public class UserRepoImpl implements UserRepo {
	@PersistenceContext
	EntityManager em;
	
	
/************** Registration*********************************************************/
	@Transactional
	public void registerUser(User user) {

		em.persist(user);
	}

	public boolean checkRegisteredUser(String email) {
		// TODO Auto-generated method stub
		String sql = "select cs from User cs where cs.email=:email";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("email", email);
		User c = new User();
		try {
			c = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		if (c.getEmail() == null) {
			return false;
		}
		return true;
	}

/**************************** Login*******************************************************/

	@Override
	public LoginStatus loginUser(String email, String password) {
		String sql = "select cs from User cs where cs.email= :email and cs.password=:pass";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("email", email);
		qry.setParameter("pass", password);
		User c = new User();
		try {
			c = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		LoginStatus log = new LoginStatus();
		if (c.getEmail() == null) {
			log.setStatus(false);
			log.setUserName("");
		} else {
			log.setStatus(true);
			log.setUserName(c.getName());
		}
		return log;
	}

/******************** Validation*********************************************************************/
	@Override
	public boolean isValidEmail(String email) {
		System.out.println(email);
		String sql = "select u from User u where u.email= :email";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("email", email);
		List<User> user = new ArrayList<>();
		try {
			user = qry.getResultList();
		} catch (NoResultException e) {

		}
		System.out.println(user);
		if (user.isEmpty())
			return false;
		return true;
	}

	@Override
	@Transactional
	public boolean changePassword(int userId, String password) {
		User usr = new User();
		usr = em.find(User.class, userId);
		usr.setPassword(password);
		em.merge(usr);
		return true;
	}

	@Override
	public int getRegisteredUserId(String email) {
		String sql = "select u from User u where u.email= :email";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("email", email);
		User c = qry.getSingleResult();
		System.out.println(c.getCustomerId());
		return c.getCustomerId();
	}

// =======================USER VALIDATION================================================================================================================//
	@Override
	public User isValidUserId(int customerId) {
		User user = new User();

		try {
			user = em.find(User.class, customerId);
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}

		return user;
	}

//==========================VALIDATE TICKET================================================================================================================//

	public boolean isValidTicket(int ticketId, int customerId) {
		String sql = "select t from Booking t where t.bookingId=:ticketId AND t.user.userId =:customerId";
		TypedQuery<Booking> qry = em.createQuery(sql, Booking.class);
		qry.setParameter("ticketId", ticketId);
		qry.setParameter("customerId", customerId);
		Booking t = new Booking();
		try {
			t = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		System.out.println(t);
		if (t.getBookingId() == 0)
			return false;

		return true;
	}

//===========================VALIDATE TICKET DATE=============================================================================================================//
	public boolean isValidTicketDate(int ticketId) {
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		String sql = "select t from Booking t where t.BookingId=:ticketId AND t.dateOfJourney >:currentDate";
		TypedQuery<Booking> qry = em.createQuery(sql, Booking.class);
		qry.setParameter("ticketId", ticketId);
		qry.setParameter("currentDate", currentDate);
		Booking t = new Booking();
		try {
			t = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		System.out.println(t);
		if (t.getBookingId() == 0)
			return false;

		return true;

	}

//==================================BOOKING===================================================================================================================//
	@Transactional
	public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger> passenger, List<Seat> seats,
			Transaction transaction) {
		ticket.setTransaction(transaction);

		ticket.setPassenger(passenger);
		ticket.setSeats(seats);
		transaction.setBooking(ticket);
		for (Passenger p : passenger) {

			p.setBooking(ticket);

		}
		for (Seat s : seats) {

			s.setBooking(ticket);
		}
		Booking t = new Booking();
		try {
			t = em.merge(ticket);

			// em.persist(ticket);

		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return t.getBookingId();
	}

//===============================SEARCH A FLIGHT===================================================================================================================//
	@Override
	public List<Flight> searchAFlight(String fromCity, String toCity, String day) {
		String sql = "select f from Flight f where f.flightStatus='Available' AND  f.fromCity=:from AND f.toCity=:to and f.flightId in(select o.flight.flightId from OperationalDays o where o.operationalDays =:day)";
		TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
		query.setParameter("from", fromCity);
		query.setParameter("to", toCity);
		query.setParameter("day", day);
		List<Flight> flight = new ArrayList<Flight>();
		flight = query.getResultList();
		for (Flight f : flight) {
			System.out.println(f);

		}
		System.out.println(flight.size());
		return flight;
	}

	public List<Integer> totalSeatsBooked(List<Flight> Flight, LocalDate dateOfJourney) {
//		System.out.println(dateOfJourney);
		List<Integer> seatsAvailable = new ArrayList<>();
		for (int i = 0; i < Flight.size(); i++) {
//			System.out.println("Inside for loop");
			Long tempseats = (Long) em.createQuery(
					"select count(s.seatId) from Seat s where (s.flight.flightId= :flightId) AND (s.dateOfJourney=:dateOfJourney)")
					.setParameter("flightId", Flight.get(i).getFlightId()).setParameter("dateOfJourney", dateOfJourney)
					.getSingleResult();
			System.out.println(tempseats);
			seatsAvailable.add((int) (Flight.get(i).getTotalSeat() - tempseats));
			System.out.println((int) (Flight.get(i).getTotalSeat() - tempseats));
		}
		return seatsAvailable;
	}

//==================================================================================================================//

	@Override
	public User findByEmailPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}


//=============================FETCH BOOKING=======================================================================//

	public List<Booking> fetchAllBookingsOfCustomer(int userId) {

		String sql = "select b from Booking b where b.user.userId=:userId";

		TypedQuery<Booking> query = em.createQuery(sql, Booking.class);

		query.setParameter("userId", userId);

		List<Booking> ticket = query.getResultList();
		System.out.println(ticket.toString());

		return ticket;

	}

//============RETURN USER ID==========================================================================================//
	public int returnUsertId(User user) {
		String query = "SELECT u FROM User u WHERE u.email =: email ";
		TypedQuery<User> query1 = em.createQuery(query, User.class);
		query1.setParameter("email", user.getEmail());
		User user1 = new User();
		user1 = query1.getSingleResult();
		return user1.getCustomerId();

	}

	// =============================================CANCEL TICKET=======================================================//
	@Override
	public boolean checkUser(int userId) {
		User u = new User();
		u = em.find(User.class, userId);
		if (u == null) {
			return false;
		}

		return true;
	}

	@Transactional
	public StatusString cancelTicket(int bookingId) {
		Booking b = new Booking();
		b = em.find(Booking.class, bookingId);
	    double initalWalletBalance=  b.getUser().getWalletBalance();
	    double bookingCost= b.getTotalCost();
	    User user=em.find(User.class,b.getUser().getCustomerId());     
	    user.setWalletBalance(initalWalletBalance+bookingCost);
	    b.setStatus(true);   
	    	    
		System.out.println(b);
		System.out.println(b.getUser().getCustomerId());
        
			List<Seat> seats = new ArrayList<>();
			List<Passenger> passengers = new ArrayList<Passenger>();
       
			Seat seat = new Seat();
			Passenger passenger = new Passenger();
       
			String sqlSeats = "select s from Seat s where s.booking.bookingId=: bookingid";
			Query qrySeats = em.createQuery(sqlSeats);
			qrySeats.setParameter("bookingid", bookingId);
			seats = qrySeats.getResultList();
			System.out.println(seats.toString());	
			for (int i = 0; i < seats.size(); i++) {
				System.out.println("inside for loop");
				try {
					em.remove(seats.get(i));
				} catch (NoResultException e) {

				}
			}
          
			
			String sqlPassenger = "select p from Passenger p where p.booking.bookingId=: bookingid";
			System.out.println("1");
			Query qryPassengers = em.createQuery(sqlPassenger);
			System.out.println("2");
			qryPassengers.setParameter("bookingid", bookingId);
			System.out.println("3");
			try {
				passengers = qryPassengers.getResultList();
			} catch (NoResultException nre) {
				// Ignore this because as per your logic this is ok!
			}
			System.out.println(passengers.toString());
			for (int i = 0; i < passengers.size(); i++) {
			
				try {
					em.remove(passengers.get(i));
				} catch (NoResultException e) {

				}
			}

			System.out.println("passenger removed");
			
			 try {
				em.merge(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 Booking b1=new Booking();
			 b1=em.find(Booking.class, bookingId);
			 System.out.println(b1.toString());
			 
			 
			 try {
					em.merge(b1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				StatusString status = new StatusString();
				status.setStatus("Your booking has been cancelled :(");
				return status;
		

		
	}

//=================FETCH SEATS=======================================================================================//
	public List<Integer> fetchNoOfSeats(int flightId, LocalDate dateOfJourney) {
		List<Integer> noOfSeats = new ArrayList<>();
		String sql = " SELECT s.seats from Seat s WHERE s.dateOfJourney=: dateOfJourney AND s.flight.flightId=: flightId";
		Query q = em.createQuery(sql);
		q.setParameter("dateOfJourney", dateOfJourney);
		q.setParameter("flightId", flightId);

		try {
			noOfSeats = q.getResultList();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return noOfSeats;

	}

//======================WALLET BALANCE===============================================================================//
	@Override
	public double showWalletBalance(int userId) {

		String sql = "select u from User u where u.userId= :UserId";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("UserId", userId);
		User cust = new User();
		try {
			cust = qry.getSingleResult();

		} catch (NoResultException e) {

		}

		return cust.getWalletBalance();
	}

	@Override
	public boolean addWalletBalance(int userId, double amount) {
		User cust = new User();
		cust = em.find(User.class, userId);
		cust.setWalletBalance(cust.getWalletBalance() + amount);

		User customer = em.merge(cust);

		if (customer.getWalletBalance() > 0)
			return true;

		return false;
	}

}
