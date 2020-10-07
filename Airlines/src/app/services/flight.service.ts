import { Injectable } from '@angular/core';
import {FlightDetails} from '../flightDetails.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http:HttpClient) { }

  flightDetails(flightDetails :FlightDetails):Observable<any>{
    return this.http.post("http://localhost:8181/searchFlight",flightDetails);
  }

  // noOfSeats(seatdetails : SeatDetails):Observable<any>{
  //   return this.http.post("http://localhost:8181/getNoOfSeats",seatdetails);
  // }

  //  bookMyticket(bookticket:BookTicket):Observable<any>
  //  {
  //    return this.http.post("http://localhost:8181/bookTicket",bookticket)
  //  }

}
