package com.lti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.Controller.AdminController;
import com.lti.Controller.UserController;
import com.lti.Service.UserService;
import com.lti.bridge.FlightDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.ShowFlightDetails;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.entity.Passenger;
import com.lti.entity.User;
import com.lti.pojo.BookTicket;
import com.lti.pojo.BookingSeatDetails;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.RemoveFlight;
import com.lti.pojo.SearchFlight;
import com.lti.pojo.TicketDetails;
import com.lti.pojo.UserDetails;
@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
class FlightApplicationTests {
 @Autowired
 UserController controller;

 @Autowired
 AdminController adminCon;
 
 @Autowired
 UserService userServ;
 
 User customer=new User();
    @Test
	public void regiterTest() {
	
//        customer.setEmail("sahisaurabh51@gmail.com");
//        customer.setPassword("sonu_3098");
//        customer.setName("Saurabh");
//        customer.setAge(22);
//        customer.setContact("8873056889");
//        customer.setGender("Male");

//    	customer.setEmail("mayankyadav51@gmail.com");
//        customer.setPassword("mayak");
//        customer.setName("mayank");
//        customer.setAge(22);
//        customer.setContact("8873056889");
//        customer.setGender("Male");
//		System.out.println(controller.registerUser(customer).getMessage());
		
		customer.setEmail("anantshadisupport@gmail.com");
        customer.setPassword("shadi kr dunga");
        customer.setName("Anant");
        customer.setAge(23);
        customer.setContact("8873056899");
        customer.setGender("Male");
		System.out.println(controller.registerUser(customer).getMessage());
		customer.setEmail("manishadesingsupport@gmail.com");
        customer.setPassword("Suno na sir bole ki");
        customer.setName("Maneesha");
        customer.setAge(23);
        customer.setContact("88730589879");
        customer.setGender("Female");
		System.out.println(controller.registerUser(customer).getMessage());
	}
    @Test
    public void testLogin()
    {
    	String username="anantshadisupport@gmail.com";
    	String password="shadi kr dunga";
//    	String username="sahisaurabh51@gmail.com";
//    	String password="sonu_3098";
    	LoginStatus status=controller.login(username, password);
    	if(status.isStatus())
    	{
    		System.out.println(status.getUserName()+", You can book now ");
    	}
    	else
    	{
    		System.out.println("Enter Valid Email/password");
    	}
    }
    
    @Test
    public void validateEmailTest()
    {
    	String email="sahisaurabh51@gmail.com";
    	System.out.println(userServ.isValidEmailId(email));
    }
    @Test
    public void testChangePassword()
    {
    	System.out.println(userServ.changed(10134,"mayank"));
    }
    //Test Services
    @Test
    public void testSearch()
    {
    	SearchFlight flight=new SearchFlight();
		
    }
    //***************************************************BOOK TICKET TEST *****************************************************//
   
    @Test
    public void TestBookingTicket() {
    BookTicket  bookticket=new BookTicket();//
    UserDetails customerDetails=new UserDetails();//
    TicketDetails ticketDetails=new TicketDetails();
    List<PassengerDetails> passengerDetails=new  ArrayList<PassengerDetails>();
    List<BookingSeatDetails> seatDetails=new ArrayList<BookingSeatDetails>();
    
    customerDetails.setContact("8873056889");
    customerDetails.setEmail("sahisaurabh51@gmail.com");
    
    LocalDate dtOfJrny=LocalDate.parse("2020-10-05");
    ticketDetails.setDateOfBooking(LocalDate.now());
    ticketDetails.setDateOfJourney(dtOfJrny);
    ticketDetails.setFlightId(131105);
    ticketDetails.setFromCity("Patna");
    ticketDetails.setToCity("Delhi");
    ticketDetails.setTotalCost(7200.00);
    ticketDetails.setNoOfSeatsBooked(2);
    
    PassengerDetails p1=new  PassengerDetails();
    PassengerDetails p2=new  PassengerDetails();
    
    p1.setAge(22);
    p1.setName("Saurabh Kumar");
    p1.setGender("male");
    p2.setAge(23);
    p2.setName("Mayank Yadav");
    p2.setGender("male");
    
    passengerDetails.add(p1);
    passengerDetails.add(p2);
    
    BookingSeatDetails b1=new BookingSeatDetails(6);
    BookingSeatDetails b2=new BookingSeatDetails(8);
    
    seatDetails.add(b1);
    seatDetails.add(b2);
    
    bookticket.setCustomerDetails(customerDetails);
    bookticket.setTicketDetails(ticketDetails);
    bookticket.setPassengerDetails(passengerDetails);
    bookticket.setSeatDetails(seatDetails);
    
    System.out.println(controller.addTicketDetails( bookticket));
    }
    
 /******************************Admin Test*****************************/
    Admin admin=new Admin();
    
    @Test
    public void testAdminRegister()
    {
    	admin.setUserName("Saurav");
    	admin.setPassword("Titans1234");
    	System.out.println(adminCon.addEmployee(admin));
    }
    @Test
    public void testAdminLogin()
    {
    	String username="Saurav";
    	String password="Titans1234";
    	System.out.println(adminCon.login(username, password));
   	
    }
    
    @Test
    public void TestRemoveFlight()
    {
    	RemoveFlight remove=new RemoveFlight();
    	remove.setFlightId(101);
    	System.out.println(adminCon.removeFlight(remove));
    }
    
    @Test
    public void TestingShowFlight()
    {
    	ShowFlightDetails details=adminCon.showFlight();
    	
    	for(Flight f: details.getFlightdetails())
    	{
    		System.out.println(f);
    	
   		
    	}
    	//System.out.println(details.toString());
    }
    
  @Test
  public void TestFlightAdd()
  {
	  LocalDate Arrva=LocalDate.of(2006,02,21);
	  Flight flight=new Flight();
	  flight.setFlightName("IndiGo");
	  flight.setFlightStatus("Availabe");
	  flight.setFromCity("Patna");
	  flight.setToCity("Delhi");
	  flight.setTotalSeat(40);
	  flight.setArrivalTime("02:30 PM");
	  flight.setDepartureTime("2:45 PM");
	  flight.setFare(3600.0);
	  flight.setDuration("2h");
	  System.out.println(adminCon.addFlight(flight)+"Successfully added");
  }
  @Test void TestOperationDays()
  {
	 	  List<OperationalDays> days=new ArrayList<OperationalDays>();
	  OperationalDays d1=new OperationalDays();
		   d1.setOperationalDays("Mon");
	   OperationalDays d2=new OperationalDays();
	   	   d2.setOperationalDays("Wed");
	   OperationalDays d3=new OperationalDays();
	  	   d3.setOperationalDays("Sat");
	   days.add(d1);
	   days.add(d2);
	   days.add(d3);
	  System.out.println(adminCon.addOperationalDaysWithFlight(days,131106));
  }
  
}
