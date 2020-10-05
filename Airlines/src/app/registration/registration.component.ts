import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';
import { Router } from '@angular/router';
import { RegisterService } from '../services/register.service';
import { formatCurrency } from '@angular/common';
import { NgForm } from "@angular/forms";
import { isGeneratedFile } from '@angular/compiler/src/aot/util';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User;
  registerStatus:string="";

  constructor(private router: Router, private registerService: RegisterService) {
    this.user = new User();
  }

  ngOnInit(): void {
  }

  signup() {
    
    this.registerService.register(this.user).subscribe(data => {
      this.registerStatus = data.message;
      if(this.registerStatus=="You have registred successfully"){
     this.router.navigate(['Login']);
      }
    })

    // form.reset();
  }


  cancel() {
    this.router.navigate(['Cancel'])
  }

}
