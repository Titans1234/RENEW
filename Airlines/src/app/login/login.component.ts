import { Component, OnInit } from '@angular/core';
import { User} from '../user.model';
import { Router } from '@angular/router' ;


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 usn :string ;
 psw: string ;
 user :User ;

  constructor(private router :Router) { }

  ngOnInit(): void {
  }

  search(usn:string){
 // this.user =  this.service.search(usn);
  }

  next(){
this.router.navigate(['FlightSelect']);
  }

}
