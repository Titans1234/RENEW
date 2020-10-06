import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RemoveFlight } from "../removeFlight.model";

@Injectable({
  providedIn: 'root'
})
export class ActivateRemoveService {
a:number ;
  constructor(private http:HttpClient) { }


  activateFlight(activateFlight : RemoveFlight):Observable<any>{
    return this.http.post("http://localhost:8181/activateFlight",activateFlight);
  }

  removeFlight(removeFlight : RemoveFlight):Observable<any>{
    console.log(removeFlight.flightId);
     this.a= removeFlight.flightId;
     console.log(this.a);
    return this.http.post("http://localhost:8181/removeFlight",this.a);
   }

   showFlight():Observable<any>{
    return this.http.get("http://localhost:8181/showFlight");
  }

}
