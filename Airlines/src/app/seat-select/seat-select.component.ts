import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SeatDetails } from '../SeatDetails.model';
import { SeatDetailsService } from '../services/seat-details.service';
import { SeatBookDetails } from "../seatBookDetails.model";
@Component({
  selector: 'app-seat-select',
  templateUrl: './seat-select.component.html',
  styleUrls: ['./seat-select.component.css']
})
export class SeatSelectComponent implements OnInit {
  tempid: any;
  fare: number = 0;
  totalFare: number = 0;
  seatDetails: SeatDetails = new SeatDetails();
  s: SeatBookDetails[] = new Array<SeatBookDetails>();
  seatBookDetails: SeatBookDetails = new SeatBookDetails();
  selected: boolean = false;
  selectSeats = new Array<string>();
  selectedSeatNumber: string;
  limitExceeds: string = "Cannot book more than 5 Seats."
  limit: boolean = false;
  economyFare: number = Number(sessionStorage.getItem('fare'));




  constructor(private seatDetailservice: SeatDetailsService, private router: Router) { }


  selectseat(idin: any) {
    console.log(idin);
    console.log(this.economyFare);
    console.log("inside select seats");
    var sliced = idin.slice(0, 1);

    this.tempid = idin;
    var id = document.getElementById(idin)
    var result = this.findIndex(idin);
    console.log(result);

    if (result >= 0) {
      this.limit = false;
      this.selectSeats.splice(result, 1);
      this.isChecked(id);


      if (this.selectSeats.length == 0) {
        this.fare = 0;
        this.totalFare = this.fare;
      }
      else if (sliced == 'E') {
        this.fare = this.fare - Number(sessionStorage.getItem('fare'));
        this.totalFare = this.fare;
      }
      else {
        this.fare = this.fare - (Number(sessionStorage.getItem('fare')) + 3000);
      }
    }

    else {
      console.log("insideElse");
      if (this.selectSeats.length == 0) {
        if (sliced == 'E') {
          console.log("Inside E");
          this.fare = Number(sessionStorage.getItem('fare'));
        }
        else {
          console.log("Inside B");
          this.fare = (Number(sessionStorage.getItem('fare')) + 3000);
        }
        this.selectSeats.push(idin);
        // this.seatBookDetails.seatNo= (idin);
        // this.s.push(this.seatBookDetails);
        // this.seatBookDetails=new SeatBookDetails;
        this.totalFare = this.fare;
        console.log(this.totalFare);
        this.isChecked(id);
      }

      else if (this.selectSeats.length <= 4) {

        this.selectSeats.push(idin);
        // this.seatBookDetails.seatNo= (idin);
        // this.s.push(this.seatBookDetails);
        // this.seatBookDetails=new SeatBookDetails;
        this.isChecked(id);

        if (sliced == 'E') {
          this.fare = this.fare + Number(sessionStorage.getItem('fare'));
        }
        else {
          this.fare = this.fare + (Number(sessionStorage.getItem('fare')) + 3000);
        }
        this.totalFare = this.fare;
      }
      else {
        this.limit = true;
      }


    }
    console.log(this.selectSeats);
    console.log(this.totalFare);
    this.selectedSeatNumber = String(this.selectSeats);

  }


  isChecked(elem) {
    elem.parentNode.style.color = (elem.checked) ? 'white' : '#2a1a25';
  }


  findIndex(id1) {

    for (let i = 0; i < this.selectSeats.length; i++) {

      if (id1 == this.selectSeats[i]) {
        return i;
      }

    }
    return -1;
  }





  changeSeatColor(seatNo) {
    var element = <HTMLInputElement>document.getElementById(seatNo);
    element.disabled = true;
  }


  key = new Array<string>();

  ngOnInit(): void {
    this.seatDetails.flightId = Number(sessionStorage.getItem('flightId'));
    this.seatDetails.dateOfJourney = String(sessionStorage.getItem('date'));
    this.seatDetailservice.noOfSeats(this.seatDetails).subscribe(data => {
      this.key = data.noOfseats;

      for (let i = 0; i < this.key.length; i++) {
        // ("for loop")
        this.changeSeatColor(this.key[i]);
      }
    })

  }


  storeSeatDetails() {
    sessionStorage.setItem("seatsBooked", JSON.stringify(this.selectSeats));
    var i;
    for (i = 0; i < this.selectSeats.length; i++) {
      this.seatBookDetails.seatNo = this.selectSeats[i];
      this.s.push(this.seatBookDetails);
      this.seatBookDetails = new SeatBookDetails();

    }
    sessionStorage.setItem('seatBookdetails', JSON.stringify(this.s));
    // sessionStorage.setItem("seatObjectList", JSON.stringify(this.s))
    // console.log(this.s);
    sessionStorage.setItem("totalFare", String(this.totalFare));
    this.router.navigate(['Passenger']);
  }

}