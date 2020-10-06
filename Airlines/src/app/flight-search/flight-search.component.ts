import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
// import { from } from 'rxjs';
import {FlightDetails} from '../flightDetails.model';
import {FlightService} from '../services/flight.service';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {
  details: FlightDetails = new FlightDetails();
  data: any;
  totalFlight:number ;
 
  constructor(private flightDetails: FlightService,private router:Router) { }

  ngOnInit(): void {const dateOfJourney = new Date(sessionStorage.getItem('date'));
  const day1 = dateOfJourney.getDay();
  var Wday: string[] = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
  var day = Wday[day1];
   (day);
  this.details.fromCity = sessionStorage.getItem('fromCity');
  this.details.toCity = sessionStorage.getItem('toCity');
  this.details.day=day;
  this.details.dateOfJourney = sessionStorage.getItem('date');
   (this.details.dateOfJourney);
   (this.details);
  this.flightDetails.flightDetails(this.details).subscribe(data => {
    this.data = data;
     (data);
    // this.totalFlight=data.length;  //idk yeh kya 
    this.totalFlight=data.length;
  })}

  // viewSeats(flightId:any,fare:any,flightName:any)
  // {
  //   sessionStorage.setItem('flightId',flightId);
  //   sessionStorage.setItem('fare',fare)
  //   sessionStorage.setItem('flightName',flightName);
  //   this.router.navigate(['SEATSELECT']);
  // }
    next(){
      this.router.navigate(['FlightSelect']);
        }

}
