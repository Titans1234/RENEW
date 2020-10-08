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


}
