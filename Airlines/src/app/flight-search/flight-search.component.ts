import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
// import { from } from 'rxjs';
import { FlightSearchService } from '../services/flight-search.service';


@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {
  fromCity: string;
  toCity: string;
  date : string;

 
  FromCities = ["Patna","Delhi", "Dehradun", "Meerut","Saharanpur"];
  constructor(private flightDetails: FlightSearchService ,private router:Router) {
    sessionStorage.removeItem('fromCity');
    sessionStorage.removeItem('toCity');
    sessionStorage.removeItem('date');
    sessionStorage.removeItem('fare')
    sessionStorage.removeItem('flightId')
   }

   setValue(){
    sessionStorage.setItem('fromCity',this.fromCity);
    sessionStorage.setItem('toCity',this.toCity);
    sessionStorage.setItem('date',this.date);
    this.router.navigate['(FlightSelect)'];
  }

  ngOnInit(): void {
  let today = new Date().toISOString().split('T')[0];
     (today);
  document.getElementsByName("trip-start")[0].setAttribute('min', today);
  if(sessionStorage.getItem('justOnce')=="false"){
    sessionStorage.setItem('justOnce',"true");
    window.location.reload();
  }

}
    next(){
      this.router.navigate(['FlightSelect']);
        }



}