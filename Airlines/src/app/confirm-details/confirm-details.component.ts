import { Component, OnInit } from '@angular/core';
import { UserDetails } from "../userDetails.model";
import { PassengerDetails } from "../passengerDetails.model";
import { SeatBookDetails } from "../seatBookDetails.model";
import { TicketDetails } from "../ticketDetails.model"
import { BookTicket } from "../bookTicket.model";
import { FlightService } from '../services/flight.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirm-details',
  templateUrl: './confirm-details.component.html',
  styleUrls: ['./confirm-details.component.css']
})
export class ConfirmDetailsComponent implements OnInit {
  noOfpassenger = new Array<number>();
  totalMembers:number;
  userDetails : UserDetails=new UserDetails();
  p :PassengerDetails[]=new Array<PassengerDetails>();
  seatbookdetails :SeatBookDetails[]=new Array<SeatBookDetails>();
  ticketdetails :TicketDetails=new TicketDetails();
  passengerdetails :PassengerDetails=new PassengerDetails();
  
  bookticket:BookTicket=new BookTicket;


constructor(private router: Router , private flightService: FlightService ) {

   }

  
  name:string;

  fare=sessionStorage.getItem("totalFare");
  seats=(JSON.parse(sessionStorage.getItem("seatsBooked")));
  dateOfJourney=sessionStorage.getItem("date");
  from=sessionStorage.getItem("fromCity");
  destination=sessionStorage.getItem("toCity");
  ticketStatus:boolean=false;
  flightName=sessionStorage.getItem("flightName");
  nameArr = new Array<string>();



  ngOnInit(): void {

    this.noOfpassenger=JSON.parse(sessionStorage.getItem("seatsBooked"));
  
    this.seatbookdetails=JSON.parse(sessionStorage.getItem("seatsBooked"));
    this.ticketdetails.flightId=Number(sessionStorage.getItem("flightId"));
    this.ticketdetails.dateOfJourney=sessionStorage.getItem("date")
    this.ticketdetails.fromCity=sessionStorage.getItem("fromCity");
    this.ticketdetails.toCity=sessionStorage.getItem("toCity");
    this.ticketdetails.totalCost=Number(sessionStorage.getItem("totalFare"));
    this.ticketdetails.noOfSeatsBooked=this.noOfpassenger.length;
    this.p=JSON.parse(sessionStorage.getItem("passengerdetails"));
  
  
   for(let i=0;i<this.p.length;i++)
   {
      this.passengerdetails=this.p[i];
      this.nameArr.push(this.passengerdetails.name);
   }

   this.name=String(this.nameArr.toString().split(","));
  }

}
