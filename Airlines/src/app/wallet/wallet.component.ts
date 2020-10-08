import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { WalletAmount } from '../walletAmount.model';
import { addAmountToWallet } from '../addAmountToWallet.model';
import {DashboardService} from '../services/dashboard.service';



@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})

export class WalletComponent implements OnInit {

walletAmount: WalletAmount = new WalletAmount();
walletBalance: number;
addAmountToWallet:addAmountToWallet=new addAmountToWallet();
amount:number;
message:string;

 constructor(private walletService: DashboardService,private router:Router) {
  if(!sessionStorage.getItem('userId')){
    this.router.navigate(['Login']); 
  }
}

  ngOnInit(): void {
    this.walletAmount.userId = Number(sessionStorage.getItem('userId'));
    console.log(this.walletAmount.userId);
   this.walletService.showWalletBalance(this.walletAmount).subscribe(data => {
     this.walletBalance = data.amount;
    
  }
 )
}

public addBalance() {
    
  this.addAmountToWallet.userId = Number(sessionStorage.getItem('userId'));
  console.log(this.addAmountToWallet.userId);
  this.addAmountToWallet.walletAmount=this.amount;
  this.walletService.addBalance(this.addAmountToWallet).subscribe(data=>{
    this.walletBalance=data.amount;
    this.message=data.status;
  })

}

}



