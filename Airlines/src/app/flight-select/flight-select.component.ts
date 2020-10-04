import { Component, OnInit } from '@angular/core';
import { Flight } from '../Admin/flight';
import { FlightModel} from '../flight.model';



@Component({
  selector: 'app-flight-select',
  templateUrl: './flight-select.component.html',
  styleUrls: ['./flight-select.component.css']
})
export class FlightSelectComponent implements OnInit {

  flight = new FlightModel;
// flights : FlightModel[] = [] ;

  constructor() { 
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
  
    
  }

}
