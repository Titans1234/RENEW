import { Component, OnInit } from '@angular/core';
import { Login } from '../login.model';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service' ;
import { identifierModuleUrl } from '@angular/compiler';
import { AdminLogin } from '../adminlogin.model';
import { AdminLoginService } from '../services/admin-login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  adminLogin: AdminLogin = new AdminLogin();
   adminMessage : string ="" ;
   adminClientStatus : boolean ;

  login: Login = new Login();
  clientstatus: boolean  ;
  userName : string ="";
  
  userMessage : string="";
  printId : number ;
  

  constructor(private router: Router, private loginService: LoginService,private adminLoginService:  AdminLoginService) { }

  ngOnInit(): void {
  }

  search(usn: string) {
    // this.user =  this.service.search(usn);
  }

  next() {
    this.router.navigate(['FlightSelect']);
  }

  loginUser() {
    this.loginService.login(this.login).subscribe(data => {
      if (!data.status) {
        this.clientstatus = data.status;
        this.userMessage="Please enter valid details!";
      }
      else if(sessionStorage.getItem('flightId')!=null) {
        
        this.clientstatus= data.status;
         let userId = data.userId;
         let userName = data.userName;
        sessionStorage.setItem('userId', userId);
        sessionStorage.setItem('userName', userName);
        sessionStorage.setItem('justOnce', "false");
        this.router.navigate(['SeatSelect']);  
        //  this.router.navigate(['FlightSearch']);
        //  this.printId =sessionStorage.getItem("userId");
      }
      else{
        let userId = data.userId;
        sessionStorage.setItem('userId', userId);
        sessionStorage.setItem('userName', this.userName);
        sessionStorage.setItem('justOnce', "false");
        this.router.navigate(['FlightSearch']);
      }
    })
  }

  adminLoginUser(){
     this.adminLoginService.adminlogin(this.adminLogin).subscribe(data =>{
        if (!data.status) {
          this.adminClientStatus= data.status ;
          this.adminMessage="Please add valid details!";
        }
        else{
        this.adminClientStatus=data.status;
         this.adminMessage=data.userName;  
        this.router.navigate(['Add']);
      }

    
     })
  }


}
