import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Airlines';

  flag:boolean=false;
  username:string;
 constructor()
 {
  
 }
ngOnInit()
{
  if(sessionStorage.getItem('userId')!=null)
  {
    this.flag=true;
    this.username=sessionStorage.getItem('userName');
  }
  else
  {
    this.flag=false;
  }
}
}
