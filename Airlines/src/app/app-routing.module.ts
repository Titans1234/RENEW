import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CancellationComponent } from './cancellation/cancellation.component';
import { LoginComponent } from './login/login.component';
import { SeatSelectComponent } from './seat-select/seat-select.component';
import { RegistrationComponent } from './registration/registration.component';
import {PaymentComponent } from './payment/payment.component';
import  { FlightSelectComponent } from './flight-select/flight-select.component';
import { FlightSearchComponent} from './flight-search/flight-search.component';
import { AddComponent } from './Admin/add/add.component';
import { DeleteComponent } from './Admin/delete/delete.component';
import {PassengerComponent} from './passenger/passenger.component';

const routes: Routes = [
  {path:'Cancel', component:CancellationComponent},
  {path:'Login', component:LoginComponent},
  {path:'SeatSelect',component:SeatSelectComponent},
  {path:'Register',component:RegistrationComponent},
  {path:'Payment',component:PaymentComponent},
  {path:'FlightSelect', component:FlightSelectComponent},
  {path:'FlightSearch', component:FlightSearchComponent},
  {path:'Add',component:AddComponent},
  {path:'Delete',component:DeleteComponent},
  {path:'Payment',component:PaymentComponent},
  {path:'Passenger',component:PassengerComponent},

//  {path:'FlightSearch', redirectTo:'/add', pathMatch :'full'},
  {path:'', redirectTo:'/FlightSearch', pathMatch :'full'}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
