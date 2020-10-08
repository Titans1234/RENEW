import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FlightResult } from '../flight-result';
//import {StatusString} from '../status';
import { Router } from '@angular/router';
import { FlightService } from "../../services/flight.service"
import { Flight } from '../flight';
@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  
   data1 : Observable<FlightResult[]>;
     data2 :any ;
  // flight: Observable<FlightResult[]>;
 // flight :FlightResult ;
 // flightDetails: FlightResult[]= [];
 // flightDetails : FlightResult[] = new Array<FlightResult>();
 // filghtIncative: FlightResult ;
 // flightInactiveDetails : FlightResult[] = new Array<FlightResult>();
  
  show: boolean;
  constructor(private flightserv: FlightService, private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
    console.log("ng in it");
    this.reloadData2();
  }

  object:boolean;
  reloadData() {
   this.object=true;
 this.data1=this.flightserv.flightResult(this.object);
    // .subscribe(data => {
    //   this.data1= data;
    //   console.log(this.data1);
    //   console.log("inside");
    //   //  this.flight = data[0] ;
    //   //  console.log(this.flight);
    //   // this.flightDetails = data ;
    //   // console.log(this.flightDetails[0]);
    //   // console.log(this.flightDetails.length);
    // }); }
  
  }

    reloadData2() {
   
      this.flightserv.activeFlightResult().subscribe( data => {
        this. data2 = data;
        console.log(this.data2);
      });
   }

  deleteflight(id: number) {
    this.flightserv.deleteFlight(id).subscribe(data => {
          console.log(data);
          this.reloadData();
          this.reloadData2();
        },
        error => console.log(error));
    window.alert("Flight is Deactivated");
  }


  addflight(id: number) {
    this.flightserv.enableFlight(id).subscribe(data => {
          console.log(data);
          this.reloadData();
         this.reloadData2();
        },
        error => console.log(error));
    window.alert("Flight is Deactivated");
  }
 

}