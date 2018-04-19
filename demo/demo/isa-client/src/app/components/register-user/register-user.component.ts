import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit() {
  }
  onSubmit(userUserName : string, userPassword : string, userName : string,userLastName : string,userCity : string,userPhoneNumber : string){
    alert("iz komponente: " + userUserName);
    this.loginService.registerUser({email:userUserName,password:userPassword,name : userName,lastName : userLastName,city : userCity, phoneNumber:userPhoneNumber}).subscribe(data => console.log(data));
    //this.router.navigateByUrl('/');*/                                                                                               
  }
}
