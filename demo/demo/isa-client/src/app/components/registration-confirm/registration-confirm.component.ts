import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import 'rxjs/add/operator/filter';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-registration-confirm',
  templateUrl: './registration-confirm.component.html',
  styleUrls: ['./registration-confirm.component.css']
})
export class RegistrationConfirmComponent implements OnInit {
  private token:string;
  constructor(private router :ActivatedRoute,private loginService:LoginService,private routerSimple:Router) { }

  ngOnInit() {
    this.router
      .queryParams
      .subscribe(params => {
        console.log(params.token);
      });
  }

  onSubmit(){
    this.loginService.confirmRegistration(this.token).subscribe(data=>{
      if(data){
        this.routerSimple.navigateByUrl("/login"); // login succeleed\
      }else{
        this.routerSimple.navigateByUrl("/public/view-cinemas"); // login unsuccessed\
      }
    })
  }
}
