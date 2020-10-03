package com.lti.email;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.lti.pojo.UserDetails;
import com.lti.pojo.PassengerDetails;
import com.lti.pojo.BookingDetails;


@Component
public class Email {

	
	 @Autowired
	 private JavaMailSender mailSender;
	 
	
	public void registerEmail(String email,String name,int customerId) {
		
	
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("saurav3097@gmail.com");
		message.setTo(email);
		message.setSubject("Welcome to your new Sukhyatara Account");
		message.setText("Hi"+" " + name + ".Thanks for Creating account.Your unique customer Id is "+customerId +"Looking Forword to your Great Journeys ahead!!!. ");
		System.out.println(message.toString()+"" + "testing");
		mailSender.send(message);
		System.out.println("Mail sent");
	}
	
	
	
	public void ticketDetailsEmail(UserDetails customerDetails, BookingDetails ticketDetails,int ticketId) {
		

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ecobusserviceddn@gmail.com");
		message.setTo(customerDetails.getEmail());
		message.setSubject("Welcome to your new ecoBus Account");
		message.setText(" Congratulations! You have booked a reschedulable ticket.   "
				+ ""
				+ ticketDetails.getFromCity()+ "====>>>" + ticketDetails.getToCity()+ " "
				+ " Date of Journey :" +ticketDetails.getDateOfJourney() + " "+ " "
				+ " PNR no :"+ticketId + ""
				+ " Bus Id :" + ticketDetails.getFlightId()+ " "
				+ " Total Seats Booked :"+ticketDetails.getNoOfSeatsBooked() +""
				+ " Total Fare :" + ticketDetails.getTotalCost() + " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " NOTE : This operator accepts mTicket, you need not carry a print out"+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ "Thank You For Booking With EcoBus  !! "
				
				
				);
		System.out.println(message.toString()+"" + "testing");
		mailSender.send(message);
		System.out.println("Mail sent");
		
	}
	
}
