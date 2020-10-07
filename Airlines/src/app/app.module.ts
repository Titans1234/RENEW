import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import  { FormsModule } from '@angular/forms' ;
import {MatDatepickerModule} from '@angular/material/datepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightSearchComponent } from './flight-search/flight-search.component';
import { FlightSelectComponent } from './flight-select/flight-select.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { PaymentComponent } from './payment/payment.component';
import { CancellationComponent } from './cancellation/cancellation.component';
import { SeatSelectComponent } from './seat-select/seat-select.component';
import { AddComponent } from './Admin/add/add.component';
import { DeleteComponent } from './Admin/delete/delete.component';
import { ViewComponent } from './Admin/view/view.component';

import { NO_ERRORS_SCHEMA,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PassengerComponent } from './passenger/passenger.component';
import { HttpClientModule } from '@angular/common/http';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { MyBookingComponent } from './my-booking/my-booking.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

@NgModule({
  declarations: [
    AppComponent,
    FlightSearchComponent,
    FlightSelectComponent,
    RegistrationComponent,
    LoginComponent,
    PaymentComponent,
    CancellationComponent,
    SeatSelectComponent,

    AddComponent,
    DeleteComponent,
    ViewComponent,
    PassengerComponent,
    UserProfileComponent,
    MyBookingComponent,
    ChangePasswordComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatDatepickerModule,
    HttpClientModule
  ],
  providers: [],
  schemas: [NO_ERRORS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
