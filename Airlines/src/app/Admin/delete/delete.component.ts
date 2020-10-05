import { Component, OnInit } from '@angular/core';
import {Flight}  from '../flight';
@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {
  
  flight=new Flight;
  constructor() { }

  ngOnInit(): void {
  }
   submitted=false;
   onSubmit()
   {
     window.alert("Flight Deleted") ;
     window.alert(this.flight.flight_no);
   }
}