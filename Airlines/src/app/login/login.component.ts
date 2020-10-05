import { Component, OnInit } from '@angular/core';
import { Login } from '../login.model';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service'
import { identifierModuleUrl } from '@angular/compiler';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login = new Login();
  clientstatus: boolean  ;
  userName : string ="";
  

  constructor(private router: Router, private loginService: LoginService) { }

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
      }
      else {
        this.clientstatus= data.status;
        let userId = data.userId;
        this.userName = data.userName;

       
         sessionStorage.setItem('userId', userId);
         sessionStorage.setItem('userName', this.userName);
         sessionStorage.setItem('justOnce', "false");
         this.router.navigate(['FlightSearch']);
      }
    })
  }

}
