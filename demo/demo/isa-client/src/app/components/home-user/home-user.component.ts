import { Component, OnInit } from '@angular/core';
import { UserProfilePageComponent } from '../user-profile-page/user-profile-page.component';
import { FriendsComponent } from '../friends/friends.component';
import { TicketPreviewComponent } from '../ticket-preview/ticket-preview.component' ;
import { ViewCinemasComponent } from '../view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from '../view-theaters//view-theaters.component';
@Component({
  selector: 'app-home-user',
  templateUrl: './home-user.component.html',
  styleUrls: ['./home-user.component.css']
})
export class HomeUserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
   
  }

 
}
