import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Profile } from '../profile.model';
import { ForgotPassword } from "../ForgotPassword.model";
import { CancelLoggedTicket } from "../CancelLoggedTicket.model";
import { ChangePassword } from '../changePassword.model' ;

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

 
  constructor(private http:HttpClient) { }

  viewProfile(profile:Profile):Observable<any>{
    return this.http.post("http://localhost:8181/viewProfile",profile);
  }

  // showWalletBalance(walletAmount:WalletAmount):Observable<any>{
  //   return this.http.post("http://localhost:8181/walletBalance",walletAmount);
  // }
  
  // addBalance(addAmountToWallet:addAmountToWallet):Observable<any>{
  //   return this.http.post("http://localhost:8181/addBalance",addAmountToWallet);
  // }

   changePassword(changePassword : ChangePassword):Observable<any>{
     return this.http.post("http://localhost:8181/changePassword",changePassword);
   }

  myBooking( mybook: ForgotPassword):Observable<any>{
    console.log("service called")
    return this.http.post("http://localhost:8181/myBookings",mybook);
  }
  
  cancelTicket(cancelTicket:CancelLoggedTicket):Observable<any>
  {
    return this.http.post("http://localhost:8181/loggedInCancel",cancelTicket);
  }

}
