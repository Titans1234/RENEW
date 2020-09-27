import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;

@Component({
  selector: 'app-cancellation',
  templateUrl: './cancellation.component.html',
  styleUrls: ['./cancellation.component.css']
})
export class CancellationComponent implements OnInit {

  constructor(private router :Router) { }

  ngOnInit(): void {
  }

  next(){
    this.router.navigate(['FlightSearch'])
  }

}
