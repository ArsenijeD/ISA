import { Component, OnInit } from '@angular/core';
import { AuthServiceService} from '../../services/auth-service.service';

@Component({
  selector: 'app-login-logout',
  templateUrl: './login-logout.component.html',
  styleUrls: ['./login-logout.component.css']
})
export class LoginLogoutComponent implements OnInit {

  private loggedUser:any;
  constructor(private authService:AuthServiceService) {
    console.log("nesto");
   }
  
  ngOnInit() {
    this.loggedUser=this.authService.getUser();
    console.log(JSON.stringify(this.loggedUser));

    console.log("nesto");
  }

  clickLogout(){
    this.authService.logoutUser();
  }
}
