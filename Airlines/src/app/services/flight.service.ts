import { Injectable } from '@angular/core';
import { FlightDetails } from '../flightDetails.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookTicket } from "../bookTicket.model";
import {Flight} from '../Admin/flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  flightDetails(flightDetails: FlightDetails): Observable<any> {
    return this.http.post("http://localhost:8181/searchFlight", flightDetails);
  }

  // noOfSeats(seatdetails : SeatDetails):Observable<any>{
  //   return this.http.post("http://localhost:8181/getNoOfSeats",seatdetails);
  // }

  bookMyticket(bookticket: BookTicket): Observable<any> {
    return this.http.post("http://localhost:8181/bookTicket", bookticket)
  }

  
  flightResult(): Observable<any> {
    console.log("showFlight");
    return this.http.get("http://localhost:8181/showFlight");
  }


  activeFlightResult(): Observable<any> {
    return this.http.get("http://localhost:8181/inactiveFlight");
  }

  addflight(flight: Flight): Observable<any> {
    console.log(flight.fare);
    return this.http.post("http://localhost:8181/addFlight", flight);
  }

  deleteFlight(id: number): Observable<any> {
    return this.http.put("http://localhost:8181/removeFlight", id);
  }

  enableFlight(id: number): Observable<any> {
    return this.http.put("http://localhost:8181/activateFlight", id);
  }
  
}
