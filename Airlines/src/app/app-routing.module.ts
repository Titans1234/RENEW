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
import {PassengerComponent} from './passenger/passenger.component';
import {UserProfileComponent } from './user-profile/user-profile.component';
import { MyBookingComponent } from './my-booking/my-booking.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ConfirmDetailsComponent } from './confirm-details/confirm-details.component';
import { ViewComponent } from './Admin/view/view.component';
import { AdminComponent } from './Admin/admin.component';
import { WalletComponent } from './wallet/wallet.component';

const routes: Routes = [
  {path:'Cancel', component:CancellationComponent},
  {path:'Login', component:LoginComponent},
  {path:'SeatSelect',component:SeatSelectComponent},
  {path:'Register',component:RegistrationComponent},
  {path:'Payment',component:PaymentComponent},
  {path:'FlightSelect', component:FlightSelectComponent},
  {path:'FlightSearch', component:FlightSearchComponent},
  {path:'Add',component:AddComponent},
  {path:'View',component:ViewComponent},
  {path:'Admin',component:AdminComponent},
  {path:'Payment',component:PaymentComponent},
  {path:'UserProfile',component:UserProfileComponent},
  {path:'MyBooking',component:MyBookingComponent},
  {path:'Wallet',component:WalletComponent},
  {path:'Passenger',component:PassengerComponent},
  {path:'ChangePassword',component:ChangePasswordComponent},
  {path:'ConfirmDetail',component:ConfirmDetailsComponent},

//  {path:'FlightSearch', redirectTo:'/add', pathMatch :'full'},
  {path:'', redirectTo:'/FlightSearch', pathMatch :'full'}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
