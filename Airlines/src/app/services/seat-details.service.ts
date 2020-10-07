import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SeatDetails } from '../SeatDetails.model';

@Injectable({
  providedIn: 'root'
})
export class SeatDetailsService {

  constructor(private http:HttpClient) { }

  noOfSeats(seatdetails : SeatDetails):Observable<any>{
    return this.http.post("http://localhost:8181/getNoOfSeats",seatdetails);
  }

}
