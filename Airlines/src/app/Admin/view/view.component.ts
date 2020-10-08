import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FlightResult } from '../flight-result';
//import {StatusString} from '../status';
import { Router } from '@angular/router' ;
import { FlightService } from "../../services/flight.service"
@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {


  flight:Observable<FlightResult[]>;
  filghtIncative:Observable<FlightResult[]>;
 // status:StatusString;
  show:boolean;
  constructor(private flightserv:FlightService,private router:Router) { 
}

    ngOnInit() {
     this.reloadData();
      this.reloadData2();
    }
    reloadData()
  {  
    
   // this.status.status="Send all flight details"; 
    this.flight=this.flightserv.flightResult();
     // this.filghtIncative=this.flightserv.activeFlightResult();
  }
   reloadData2()
   {
   //this.filghtIncative=this.flightserv.activeFlightResult();
    this.flightserv.activeFlightResult().subscribe((data) => {
      this.filghtIncative = data;
     console.log(this.filghtIncative);
 });
   }
  
  deleteflight(id: number) {
    this.flightserv.deleteFlight(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
          this.reloadData2();
        },
        error => console.log(error));
        window.alert("Flight is Deactivated");
  }
  addflight(id:number)
  {
    this.flightserv.enableFlight(id)
    .subscribe(
      data => {
        console.log(data);
        this.reloadData();
        this.reloadData2();
      },
      error => console.log(error));
      window.alert("Flight is Deactivated");
}
     //  (data);
      // this.totalFlight=data.length;     //this.totalFlight=data.length;
    
}