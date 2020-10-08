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
  constructor(private flightadd: FlightService,private router:Router) {  }

  ngOnInit(): void {
  }
  addFlight()
  {
    this.flightadd.addflight(this.flight).subscribe(data => {
      this.status= data;   
      
    })}
  
  
  addOperationalDays()
  {
        
  }
}