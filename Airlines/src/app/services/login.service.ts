import { Injectable } from '@angular/core';
import {Login} from '../login.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  login(login :Login):Observable<any>{
  
    return this.http.post("http://localhost:8181/login",login);
  }
}
