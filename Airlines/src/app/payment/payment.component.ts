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
  noOfpassenger = new Array<string>();
  totalMembers: number;
  userDetails: UserDetails = new UserDetails();

  s: SeatBookDetails;
  p: PassengerDetails[] = new Array<PassengerDetails>();
  s1: SeatBookDetails[] = new Array<SeatBookDetails>();
  seatbookdetails: SeatBookDetails[] = new Array<SeatBookDetails>();
  ticketdetails: TicketDetails = new TicketDetails();
  passengerdetails: PassengerDetails = new PassengerDetails();

  bookticket: BookTicket = new BookTicket;

  ticketStatus: boolean = false;

  constructor(private router: Router, private flightDetails: FlightService) { }

  ngOnInit(): void {

    this.noOfpassenger = JSON.parse(sessionStorage.getItem("seatsBooked"));
    console.log(this.noOfpassenger);
    for (let i = 0; i < this.noOfpassenger.length; i++) {
      this.s.seatNo = String (this.noOfpassenger[i]);
      this.seatbookdetails.push(this.s);
      this.s = new SeatBookDetails();
    }
      console.log(this.seatbookdetails);
      console.log(this.userDetails.userId);
      this.seatbookdetails = JSON.parse(sessionStorage.getItem("seatsBooked"));
      console.log(this.seatbookdetails);
      this.ticketdetails.flightId = Number(sessionStorage.getItem("flightId"));
      this.ticketdetails.dateOfJourney = sessionStorage.getItem("date")
      this.ticketdetails.fromCity = sessionStorage.getItem("fromCity");
      this.ticketdetails.toCity = sessionStorage.getItem("toCity");
      this.ticketdetails.totalCost = Number(sessionStorage.getItem("totalFare"));
      this.ticketdetails.noOfSeatsBooked = this.noOfpassenger.length;
      this.p = JSON.parse(sessionStorage.getItem("passengerdetails"));
      this.s1 = JSON.parse(sessionStorage.getItem("seatObjectList"));

    }
  


    payNow()
    {
      this.bookticket.userDetails = this.userDetails;
      this.bookticket.passengerDetails = this.p;
      this.bookticket.seatDetails = this.seatbookdetails;
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
