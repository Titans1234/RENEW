import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';
import { Router } from '@angular/router' ;

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
user : User ;

  constructor(private router : Router ) {
    this.user = new User();
   }

  ngOnInit(): void {
  }

  signup(){
    this.router.navigate(['FlightSearch']);

  }

  cancel(){
    this.router.navigate(['Cancel'])
  }

}
