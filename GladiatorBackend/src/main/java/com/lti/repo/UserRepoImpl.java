package com.lti.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.bridge.LoginStatus;
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
	
   /************Registration***********/
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
	
	
/*************************Login*****************************/

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
		LoginStatus log=new LoginStatus();
		if(c.getEmail()==null)
		{
			log.setStatus(false);
			log.setUserName("");
		}
		else
		{
			log.setStatus(true);
			log.setUserName(c.getName());
		}
		return log;
	}
/*****************Validation*****************/
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
//////////////////////////////////////////////////////////
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
	 public boolean isValidTicketDate(int ticketId)
	 {
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
	 
	 @Override
	public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger>passenger,List<Seat>seats,Transaction transaction) {
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
			} catch (NoResultException e) {
				// TODO: handle exception
			}
			return t.getBookingId();
		}

	 
	 
	 @Override
		public List<Flight> searchAFlight(String fromCity, String toCity, String day) {
			String sql = "select f from Flight f where f.FlightStatus='active' AND  f.fromCity=:from AND f.toCity=:to and f.flightId in(select o.Flight.flightId from OperationalDays as o where o.operationalDays=:day))";
			TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
			query.setParameter("from", fromCity);
			query.setParameter("to", toCity);
			query.setParameter("day", day);
			List<Flight> flight = query.getResultList();
			return flight;
		}

		public List<Flight> searchRoutesByFlight(List<Integer> flightId, String fromCity, String toCity) {
			List<Flight> routeDetails = new ArrayList<>();
//			for (int i = 0; i < flightId.size(); i++) {
	//
//				String sql = "select f from Flight f where (f.flightId=:flightId) AND (f.fromCity=:fromCity) AND (f.toCity=:toCity)";
//				TypedQuery<Flight> query = em.createQuery(sql, Flight.class);
//				query.setParameter("flightId", flightId.get(i));
//				query.setParameter("fromCity", fromCity);
//				query.setParameter("toCity", toCity);
	//
//				Flight routes = query.getSingleResult();
//				routeDetails.add(routes);
//			}
			return routeDetails;
		}
		
	public List<Integer> totalSeatsBooked(List<Flight> Flight, LocalDate dateOfJourney) {
			System.out.println(dateOfJourney);
			List<Integer> seatsAvailable = new ArrayList<>();
			for (int i = 0; i < Flight.size(); i++) {

				Long tempseats = (Long) em.createQuery(
						"select count(s.seatId) from Seats s where (s.Flight.flightId= :flightId) AND (s.dateOfJourney=:dateOfJourney)")
						.setParameter("flightId", Flight.get(i).getFlightId()).setParameter("dateOfJourney", dateOfJourney)
						.getSingleResult();
				System.out.println(tempseats);
				seatsAvailable.add((int) (Flight.get(i).getTotalSeat() - tempseats));
				System.out.println((int) (Flight.get(i).getTotalSeat() - tempseats));
			}
			return seatsAvailable;
		}
	
	@Override
	public List<Booking> fetchBookingsOfCustomer(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User findByEmailPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Booking searchTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean cancelTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
