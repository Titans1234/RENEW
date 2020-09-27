import { Component, OnInit } from '@angular/core';
import {Flight}  from '../flight';
@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  flight=new Flight;
  constructor() { 
   this.flight.flight_no=0;
   this.flight.source;
   this.flight.destination;
  }

  ngOnInit(): void {
  }
  addFlight()
  {
    console.log(this.flight.flight_no);
    console.log(this.flight.source);
    console.log(this.flight.destination); 
  }
}
