import { Component, OnInit } from '@angular/core';
import { UserDetails } from "../userDetails.model";
import { PassengerDetails } from "../passengerDetails.model";
import { SeatBookDetails } from "../seatBookDetails.model";
import { TicketDetails } from "../ticketDetails.model"
import { BookTicket } from "../bookTicket.model";
import { FlightService } from '../services/flight.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  // noOfpassenger = new Array<string>();
  noOfpassenger : string[] ;
  totalMembers: number;
  customer : UserDetails = new UserDetails() ;

  s: SeatBookDetails;
  p: PassengerDetails[] = new Array<PassengerDetails>();
  s1: SeatBookDetails[] = new Array<SeatBookDetails>();
  // seatbookdetails: SeatBookDetails[] = new Array<SeatBookDetails>();
  ticketdetails: TicketDetails = new TicketDetails();
  passengerdetails: PassengerDetails = new PassengerDetails();

  bookticket: BookTicket = new BookTicket;

  ticketStatus: boolean = false;

  constructor(private router: Router, private flightDetails: FlightService) { }

  ngOnInit(): void {

    this.noOfpassenger = JSON.parse(sessionStorage.getItem("seatsBooked"));
    console.log(this.noOfpassenger);
    // for (let i = 0; i < this.noOfpassenger.length; i++) {
    //   this.s.seatNo = JSON.parse(this.noOfpassenger[i]);
    //    this.seatbookdetails.push(this.s);
    //     this.s = new SeatBookDetails();
    //   console.log(JSON.stringify(this.noOfpassenger[i]));
    // }
     // console.log(this.seatbookdetails);
      this.customer.userId= Number(sessionStorage.getItem("userId"));
      console.log(this.customer);
      this.s = JSON.parse(sessionStorage.getItem("seatsBooked"));
      console.log(this.s);
      this.ticketdetails.flightId = Number(sessionStorage.getItem("flightId"));
      this.ticketdetails.dateOfJourney = sessionStorage.getItem("date")
      this.ticketdetails.fromCity = sessionStorage.getItem("fromCity");
      this.ticketdetails.toCity = sessionStorage.getItem("toCity");
      this.ticketdetails.totalCost = Number(sessionStorage.getItem("totalFare"));
      this.ticketdetails.noOfSeatsBooked = this.noOfpassenger.length;
      this.p = JSON.parse(sessionStorage.getItem("passengerdetails"));
      this.s1 = JSON.parse(sessionStorage.getItem("seatBookdetails"));

    }
  


    payNow()
    {
      console.log(this.customer);

      this.bookticket.customerDetails = this.customer;
      console.log(this.bookticket.customerDetails);
      this.bookticket.passengerDetails = this.p;
      this.bookticket.seatDetails = this.s1;
      this.bookticket.ticketDetails = this.ticketdetails;
      console.log(this.bookticket);
      this.flightDetails.bookMyticket(this.bookticket).subscribe(data => {
        (data);
        if (!data.resultStatus) {
          this.ticketStatus = data.resultStatus;
        }
        else {
          this.ticketStatus = data.resultStatus;

        }
      })
      //   sessionStorage.clear();
    }
  }
