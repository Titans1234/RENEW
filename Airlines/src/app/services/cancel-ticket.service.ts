import { Injectable } from '@angular/core';
import {CancelTicket} from '../cancelTicket.model';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CancelTicketService {

  constructor(private http:HttpClient) { }

  cancelTicket(ct:CancelTicket):Observable<any>
  {
    return  this.http.post("http://localhost:8181/cancelTicket",ct);
  }
}
