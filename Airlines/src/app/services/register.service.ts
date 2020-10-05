import { Injectable } from '@angular/core';
import {  User} from '../user.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http:HttpClient) { }

  register(user: User):Observable<any>{
    return this.http.post("http://localhost:8181/register",user);
}

}
