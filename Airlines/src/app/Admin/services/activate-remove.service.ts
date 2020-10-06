import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RemoveFlight } from "../removeFlight.model";

@Injectable({
  providedIn: 'root'
})
export class ActivateRemoveService {

  constructor(private http:HttpClient) { }


  activateFlight(activateFlight : RemoveFlight):Observable<any>{
    return this.http.post("http://localhost:8181/activateFlight",activateFlight);
  }

  removeFlight(removeFlight : RemoveFlight):Observable<any>{
    console.log(removeFlight.flightId);
    return this.http.post("http://localhost:8181/removeFlight",removeFlight);
   }

   showFlight():Observable<any>{
    return this.http.get("http://localhost:8181/showFlight");
  }

}
