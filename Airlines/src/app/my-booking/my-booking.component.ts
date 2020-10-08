import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from "../services/dashboard.service";
import { ForgotPassword } from "../ForgotPassword.model";
import { CancelLoggedTicket } from "../CancelLoggedTicket.model";

@Component({
  selector: 'app-my-booking',
  templateUrl: './my-booking.component.html',
  styleUrls: ['./my-booking.component.css']
})

export class MyBookingComponent implements OnInit {

  mybook:ForgotPassword=new ForgotPassword();

  constructor(private router:Router,private service:DashboardService) {
    
    if(!sessionStorage.getItem('userId')){
      this.router.navigate(['loginCustomer']);
    }
   }


   bookings:any;
   status:boolean;
   canticket:CancelLoggedTicket=new CancelLoggedTicket();
   cancelstatus : string;


  ngOnInit(): void {
    
    this.mybook.userId=Number(sessionStorage.getItem('userId'));
      this.service.myBooking(this.mybook).subscribe(data =>{
        console.log(data);
        console.log(data.resultStatus)
        console.log(data.cancelButton);
        if(data.resultStatus)
        {
          this.status=true;
          this.bookings=data.mybookings;
          console.log(data.mybookings)
          console.log(data.resultStatus)
        }
        else{
        this.status=false;
        }
      })
  }

  cancelTicket(ticketId){
    this.canticket.bookingId=ticketId;
    this.service.cancelTicket(this.canticket).subscribe(data=>{
    this.cancelstatus=data.status;
    console.log(data.cancelButton);
      if(this.cancelstatus!=null)
      {
        console.log("cancel");
     
        window.location.reload();
      }
    })
  }

  
  logout()
  {
    sessionStorage.removeItem('fromCity');
    sessionStorage.removeItem('toCity');
    sessionStorage.removeItem('date');
    sessionStorage.removeItem('fare');
    sessionStorage.removeItem('flightId');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('userName');
    sessionStorage.removeItem('justOnce');
    sessionStorage.removeItem('passengerdetails');
    sessionStorage.removeItem('seatBookdetails');
    sessionStorage.removeItem('flightName');
    this.router.navigate(['FlightSearch']);
  }
}