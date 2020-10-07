import { Component, OnInit } from '@angular/core';
import { UserDetails } from "../userDetails.model";
import { PassengerDetails } from "../passengerDetails.model";
import { SeatBookDetails } from "../seatBookDetails.model";
import { TicketDetails } from "../ticketDetails.model"
import { BookTicket } from "../bookTicket.model";
import {FlightService} from '../services/flight.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.css']
})
export class PassengerComponent implements OnInit {
  noOfpassenger = new Array<string>();
  totalMembers: number;

  userdetails: UserDetails = new UserDetails();
  p: PassengerDetails[] = new Array<PassengerDetails>();
  seatbookdetails: SeatBookDetails[] = new Array<SeatBookDetails>();
  ticketdetails: TicketDetails = new TicketDetails();
  passengerdetails: PassengerDetails = new PassengerDetails();

  bookticket: BookTicket = new BookTicket();

  constructor(private flightDetailService: FlightService,private router:Router) { }
  
  
  seat:string ;
  counter: number = 0;


  ngOnInit(): void {
    this.noOfpassenger=JSON.parse(sessionStorage.getItem('seatsBooked'));
    this.seat= this.noOfpassenger[0];
  
    this.totalMembers=this.noOfpassenger.length;
    
  }

  bookTicket(){

    sessionStorage.setItem('passengerdetails',JSON.stringify(this.p)); 
    this.router.navigate(['ConfirmDetail']);
  }

  ADD(){
    console.log("alert");
    if(this.noOfpassenger.length-1 >= this.counter){
      this.counter++ ;
      console.log("inside add pass");
      this.seat=this.noOfpassenger[this.counter];
      this.p.push(this.passengerdetails);
      console.log(this.p);
      this.passengerdetails=new PassengerDetails();
      if(this.counter == this.noOfpassenger.length){
        var element = <HTMLInputElement>document.getElementById('addPass');

        element.disabled=true ;
      }
    }
  }

// isNumber(event, id ,1){
//   var mobile =<HTMLInputElement>document.getElementById(id);
//   var data = mobile.value;
//   var key= event.key;
//   if(isNaN(key) || data.length > 1) event.preventDefault();
// }

// manageMobile(id){
//   var mobile= <HTMLInputElement>document.getElementById(id);
//   var data=mobile.value;
//   var key = Event.key ;
//   if(isNaN(key) || data.length > 1) Event.preventDefault();
// }


// manageMobile(id) {
//   var mobile = <HTMLInputElement>document.getElementById(id);
//   var data = mobile.value;
//   data = data.replace(/ /g, '');
//   if (data.length <= 5) mobile.value = data;
//   else
//     mobile.value =
//       data.substring(0, 5) + ' ' + data.substring(5, data.length);
// }

// isAName(event) {
//   var key = event.key;
//   if (
//     (key >= 'a' && key <= 'z') ||
//     (key >= 'A' && key <= 'Z') ||
//     key == ' '
//   ) {
//   } else {
//     event.preventDefault();
//   }
// }

}
