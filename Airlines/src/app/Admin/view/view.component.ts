import { Component, OnInit } from '@angular/core';
import {ActivateRemoveService} from '../services/activate-remove.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
flight :any ;
  constructor(private service : ActivateRemoveService) { }

  ngOnInit(): void {
    this.service.showFlight().subscribe(data=>{
      this.flight=data.flightdetails;
      console.log(data);
      console.log(this.flight);

    })
  }

}
