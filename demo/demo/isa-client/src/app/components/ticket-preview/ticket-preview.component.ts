import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-ticket-preview',
  templateUrl: './ticket-preview.component.html',
  styleUrls: ['./ticket-preview.component.css']
})
export class TicketPreviewComponent implements OnInit {
  private ticketArray : any[] = [];

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.userService.getAllTickets().subscribe(data=>{this.ticketArray=data});
  }
  cancleTicket(ticketId : any){
    this.userService.cancleTicket(ticketId).subscribe(
      data=>{
          if(data){
            console.log("successful");
          }
          else{
            console.log("failed");
          }
      });
  }
}
