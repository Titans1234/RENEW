import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { RemoveFlight } from '../removeFlight.model'
import {ActivateRemoveService} from '../services/activate-remove.service';
@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {
  removeFlight: RemoveFlight = new RemoveFlight();
   addFlight :  RemoveFlight = new RemoveFlight();
  
  constructor(private service : ActivateRemoveService) { }


  ngOnInit(): void {
  }
   submitted=false;
   onSubmit()
   {
     window.alert("Flight Deleted") ;
     window.alert(this.removeFlight.flightId);
   }


  //  accFlight(){
  //   this.service.activateFlight(this.addFlight).subscribe(data=>{
  //     if(data.resultStatus){
  //       window.location.reload();
  //     }
  //  })
  // }

   Deactivate(){
     console.log(this.removeFlight.flightId);
     this.service.removeFlight(this.removeFlight).subscribe(data =>
      { 
        if(data.resultStatus)
        {
          window.location.reload();

        }
      })
    }
    
 
   
   
}