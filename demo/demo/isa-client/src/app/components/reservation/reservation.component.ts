import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';
import { AuthServiceService} from '../../services/auth-service.service';
import { OrderPipe } from 'ngx-order-pipe';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  private allCinemas:any[] = [];
  private numSeat:any;  
  private loggedInUser : any;
  result: number[];

  constructor(private orderPipe: OrderPipe,private router : Router, private cinemaService : CinemaService, private modalService: NgbModal, private authService:AuthServiceService) { 
  }

  ngOnInit() {
    this.loggedInUser = this.authService.getUser();

    this.cinemaService.getCinemas().subscribe(
      data => 
      {
        this.allCinemas = data;
        console.log("all cinemas:"+JSON.stringify(this.allCinemas));
        }
      )
    }

    onClickReserve(p,t) {
      this.cinemaService.reserveTicket({user_id:this.loggedInUser.id, projection_id:p.id,ticket_id:t.id})
      .subscribe(data =>
        {
          console.log(data);
          if(!data){
            alert("No more tickets for reservation!");
          }else{
            this.router.navigateByUrl("/reservation");
          }
        }
      );

    }


  }

