import { Injectable } from '@angular/core';
import {AdminLogin} from '../adminlogin.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminLoginService {

  constructor(private http:HttpClient) { }

  adminlogin(adminlogin : AdminLogin):Observable<any>{
    //  ("inside admin service")
    return this.http.post("http://localhost:8181/loginAdmin",adminlogin);
  }
}
