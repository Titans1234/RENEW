import { Component, OnInit } from '@angular/core';
import { Profile } from '../profile.model';
import { ViewProfileCard }  from '../profileCard.model';
import { DashboardService } from '../services/dashboard.service';
import { Router } from '@angular/router';
import { textChangeRangeIsUnchanged } from 'typescript';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  profile:Profile=new Profile();
  viewProfile:ViewProfileCard=new ViewProfileCard();
  name:string;
	dateOfBirth:string;
	gender:string;
	email:string;
  contact:string;
  
  constructor(private profileService:DashboardService,private router:Router) { 
    if(!sessionStorage.getItem('userId')){
     this.router.navigate(['Login']);
   }
  }
  ngOnInit(): void {
    this.profile.userId=Number(sessionStorage.getItem('userId'));
    this.profileService.viewProfile(this.profile).subscribe(data=>{
      
      this.viewProfile.name=data.name;
      this.viewProfile.age=data.age;
      this.viewProfile.gender=data.gender;
      this.viewProfile.email=data.email;
      this.viewProfile.contact=data.contact;
    })
  }

}

