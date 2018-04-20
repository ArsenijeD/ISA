import { Component, OnInit } from '@angular/core';
import { FriendsService } from '../../services/friends.service'
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  private usersArray : any[] = [];
  private allFriendsArray : any[] = [];
  private allFriendsReqSentArray : any[] = [];
  private allFriendsReqReceivedArray : any[] = [];

  constructor(private friendService:FriendsService) { }

  ngOnInit() {
    this.friendService.getAllFriends().subscribe(data=>{this.allFriendsArray=data;console.log("req sent:"+this.allFriendsArray);});
    this.friendService.getAllFriendReqSent().subscribe(data=>{this.allFriendsReqSentArray=data;console.log("req sent:"+this.allFriendsArray);});
    this.friendService.getAllFriendReqReceived().subscribe(data=>{this.allFriendsReqReceivedArray=data;    console.log("rec sent:"+this.allFriendsReqReceivedArray); });
    //console.log("req sent:"+this.allFriendsArray);
//    console.log("rec sent:"+this.allFriendsReqReceivedArray);
  }

  searchClicked(searchContent:String){
    this.friendService.searchUsers(searchContent.split(' ')[0],searchContent.split(' ')[1])
    .subscribe(
      data=> 
      {
        this.usersArray = data;    
        console.log(data);
      }
    );
  }

  unfriend(requestId:number){
    this.friendService.unfriend(requestId)
    .subscribe(
      data=> 
      {
        console.log(data);
      }
    );
  }
  deleteSentRequest(requestId:number){
    this.friendService.deleteSentRequest(requestId)
    .subscribe(
      data=> 
      {
        console.log(data);
      }
    );
  }

  rejectRequest(requestId:number){
    this.friendService.rejectRequest(requestId)
    .subscribe(
      data=> 
      {
        console.log(data);
      }
    );
  }

  approveRequest(requestId:number){
    this.friendService.approveRequest(requestId)
    .subscribe(
      data=> 
      {
        console.log(data);
      }
    );
  }

  sendRequest(requestId:number){
    this.friendService.sendRequest(requestId)
    .subscribe(
      data=> 
      {
        console.log(data);
      }
    );
  }

}
