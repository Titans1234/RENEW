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

  private baseUrl = "http://localhost:8181";

  flightResult(): Observable<any> {
    return this.http.get(this.baseUrl + "/showFlight" );
  }
  activeFlightResult(): Observable<any> {
    return this.http.get(`${this.baseUrl + "/inactiveFlight"}`);
  }

  addflight(flight: Flight): Observable<any> {
    console.log(flight.fare);
    return this.http.post(`${this.baseUrl + "/addFlight"}`, flight);
  }

  deleteFlight(id: number): Observable<any> {
    return this.http.put(`${"http://localhost:8181/tobackend/api/v1/removeFlight"}`, id);
  }

  enableFlight(id: number): Observable<any> {
    return this.http.put(`${"http://localhost:8181/tobackend/api/v1/activateFlight"}`, id);
  }
  
}
