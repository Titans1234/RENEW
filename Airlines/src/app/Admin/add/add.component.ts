import { Component, OnInit } from '@angular/core';
import {Flight}  from '../flight';
import { Router } from '@angular/router' ;
import { FlightService } from "../../services/flight.service"



@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  flight=new Flight;
  status:boolean;
  show:boolean=false;
  constructor(private flightadd: FlightService,private router:Router) { 
   
   //this.Added=false;
  }

  ngOnInit(): void {
  }
  addFlight()
  {
    //window.alert(this.day1);
   // window.alert(this.flight.flightno+":"+this.flight.fromCity+this.flight.toCity+" "+this.flight.fare+" "+this.flight.duration);
   // window.alert(this.flight.arrivalTime+":"+this.flight.departureTime+this.flight.flightName+" "+this.flight.totalSeat)
    this.flightadd.addflight(this.flight).subscribe(data => {
      this.status= data;
      // this.totalFlight=data.length;  //idk yeh kya 
      
    })}
   // this.show=true;
  
  addOperationalDays()
  {
        
  }
}