import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../../services/auth-service.service';
import { LoginService } from '../../services/login.service';
import {FormsModule} from '@angular/forms'
import { UserService } from '../../services/user.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.css']
})
export class UserProfilePageComponent implements OnInit {
  userEdit:any;
  disableEditing:boolean;
  constructor(private router : Router, private auth:AuthServiceService,private userService:UserService ) {
 
    this.disableEditing=true;
   }
  
  ngOnInit() {
    this.userEdit={};
    this.userEdit.firstName=this.auth.getUser().firstName;
    this.userEdit.lastName=this.auth.getUser().lastName;
    this.userEdit.phoneNumber=this.auth.getUser().phoneNumber;
    this.userEdit.city=this.auth.getUser().city;
   console.log("user je "+JSON.stringify(this.userEdit));
  }

  saveChanges(){
    if(this.disableEditing)
      {
        this.disableEditing=false
      }
    else{
      this.disableEditing=true
    }
    //this.userEdit.id=this.auth.getUser().id;
    //this.userService.updateUserInfo(this.userEdit).subscribe(data=>
     this.userService.updateUserInfo(this.userEdit).subscribe(data=>
    console.log("pozvan save"+JSON.stringify(this.userEdit)));
  }
  disableEditingClick(){
    if(this.disableEditing)
      {
        this.disableEditing=false
      }
    else{
      this.disableEditing=true
    }
  }

  goToFanZone() {

    this.router.navigateByUrl('/fanZone');
  }
}
