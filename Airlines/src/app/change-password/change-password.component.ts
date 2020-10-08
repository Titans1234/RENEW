import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../services/dashboard.service';
import { ChangePassword } from '../changePassword.model' ;
import { Router } from '@angular/router';
@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  password:ChangePassword=new ChangePassword();
  message:string;
  confirmPass="";

  constructor(private changePassword:DashboardService,private router:Router) { 
    if(sessionStorage.getItem('userId')==null){
      this.router.navigate(['loginCustomer']);
    }
  }

  
  changePasswordFunction(){
    console.log(this.password)
    this.password.userId=Number(sessionStorage.getItem('customerId'));
    this.changePassword.changePassword(this.password).subscribe(data=>{
     this.message=data.status;
    })
  }


  ngOnInit(): void {
  }

  
  confirmPassword(): boolean {
    if (this.password.newPassword == this.confirmPass){
     
      return true;      
    }
    return false;
  
  }
  logout()
  {
    sessionStorage.removeItem('fromCity');
    sessionStorage.removeItem('toCity');
    sessionStorage.removeItem('date');
    sessionStorage.removeItem('fare');
    sessionStorage.removeItem('flightId');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('userName');
    sessionStorage.removeItem('justOnce');
    sessionStorage.removeItem('passengerdetails');
    sessionStorage.removeItem('seatBookdetails');
    sessionStorage.removeItem('flightName');
    this.router.navigate(['FlightSearch']);
  }
}
