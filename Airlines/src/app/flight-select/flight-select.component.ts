import { Component, OnInit } from '@angular/core';
import { FlightModel} from '../flight.model';
import {FlightDetails} from '../flightDetails.model';
import {FlightService} from '../services/flight.service';
import { Router }  from '@angular/router';
import {IncomingFlightDetails} from '../incomingflightDetails.model';

@Component({
  selector: 'app-flight-select',
  templateUrl: './flight-select.component.html',
  styleUrls: ['./flight-select.component.css']
})
export class FlightSelectComponent implements OnInit {
  details: FlightDetails = new FlightDetails();
  data: any;
  totalFlight:number ;
  flight = new FlightModel();
// flights : FlightModel[] = [] ;
 inputValue : IncomingFlightDetails [] ;

  constructor(private flightDetailService: FlightService,private router:Router) { 
  this.flight.flightno="FE123",
  this.flight.From="Mumbai",
  this.flight.To= "Hyderabad",
  this.flight.DepartureTime = "08:45",
  this.flight.ArrivalTime= "14:45",
  this.flight.Duration = "6 hrs",
  this.flight.EconomyPrice = 5000,
  this.flight.BusinessPrice = 6650
  }

  ngOnInit(): void {
    const dateOfJourney = new Date(sessionStorage.getItem('date'));
    const day1 = dateOfJourney.getDay();
    var Wday: string[] = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    var day = Wday[day1];
    //  (day);
    this.details.fromCity = sessionStorage.getItem('fromCity');
    this.details.toCity = sessionStorage.getItem('toCity');
    this.details.day=day;
    this.details.dateOfJourney = sessionStorage.getItem('date');
    //  (this.details.dateOfJourney);
    //  (this.details);
    this.flightDetailService.flightDetails(this.details).subscribe(data => {
      this.inputValue = data;
      
     //  (data);
      // this.totalFlight=data.length; 
      this.totalFlight=data.length;
    })
    
  }


  viewSeats(flightId:any,fare:any,flightName:any)
  {  
    console.log("inside viewSeats")
    sessionStorage.setItem('flightId',flightId);
    console.log(flightId)
    sessionStorage.setItem('fare',fare)
    sessionStorage.setItem('flightName',flightName);
    if(sessionStorage.getItem('userId')!=null){
      console.log("insideIf")
    this.router.navigate(['SeatSelect']);
  }
  else{
    console.log("insideElse")
    this.router.navigate(['Login']);
  }
  }

}
