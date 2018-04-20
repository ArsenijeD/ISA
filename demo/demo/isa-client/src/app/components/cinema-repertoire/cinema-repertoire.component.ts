import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { AuthServiceService} from '../../services/auth-service.service';


@Component({
  selector: 'app-cinema-repertoire',
  templateUrl: './cinema-repertoire.component.html',
  styleUrls: ['./cinema-repertoire.component.css'],
})
export class CinemaRepertoireComponent implements OnInit {

  private currentCinema: any;
  private hallsArray : any;

  private filmsArray : any[] = [];


  private film : any;
  private hall : any;
  private date : any;
  private modified_date : any;
  private time = {hour: 18, minute: 30};
  private modified_time : any;
  private discount : any;


  private change_projection : any;
  private change_film : any;
  private change_hall : any;
  private change_date : any;
  private change_modified_date : any;
  private change_time : any;
  private change_modified_time : any;
  private change_discount : any;

  private change_old_hallID : any;

  
  private fast_tickets_number : any;
  
  private fastTickets_hall: any;
  private fastTickets_projection: any;

  private loggedInUser : any;
  private isAdmin = true;          // ovo promeni posle na true !!!

  constructor(private router : Router, private cinemaService : CinemaService, private modalService: NgbModal, private authService:AuthServiceService) { }


  ngOnInit() {

    this.loggedInUser = this.authService.getUser();
    console.log(this.loggedInUser);

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {
        this.currentCinema = currentCinema;
        console.log(currentCinema);

        for (let i = 0; i < this.currentCinema.admins.length; i++) {
          if(this.currentCinema.admins[i].id==this.loggedInUser.id){
            console.log("nasao admina pozorista!");
              this.isAdmin = false;
          } else {
            console.log("Nije nasao admina pozorista!");
            this.isAdmin = true;
          }
        }
      }
    );

    
    this.cinemaService.getFilms()
      .subscribe(
        data=> 
        {this.filmsArray = data;    
          console.log(data);
        }
      );
  
      
  }

  onClickDeleteProjection(cinemaID:any, hallID:any, projectionID:any) : void {
    
    this.cinemaService.deleteProjectionById(cinemaID, hallID, projectionID)
    .subscribe(data =>
      {
        console.log(data);
        this.currentCinema = data;
      }
    );

    this.router.navigateByUrl('/cinema-repertoire');
  
  }


  //modalni dijalozi

  onClickAddProjection(AddProjectionModal) {
    this.modalService.open(AddProjectionModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  addProjectionSubmit() {

    this.modified_date = this.date.day + "-" + this.date.month + "-" + this.date.year;
    this.modified_time = this.time.hour + ":" + this.time.minute;

    this.cinemaService.registerProjection({cinema_id: this.currentCinema.id, hall_id : this.hall, film_id : this.film , date :  this.modified_date, time : this.modified_time, discount : this.discount})
    .subscribe(data =>
      {
        console.log(data);
        this.currentCinema = data;
      } 
    );

    this.router.navigateByUrl('/cinema-repertoire');
  
  }


  onClickChangeProjection(h, p, ChangeProjectionModal) {

    this.change_old_hallID = h.id;

    this.change_projection = p.id;

    this.change_film = p.film.id;
    this.change_hall = h.id;
    this.change_discount = p.discount;
    let splitted_date = p.date.split("-");
    this.change_date = {year: Number(splitted_date[2]), month: Number(splitted_date[1]), day:Number(splitted_date[0])};
    let splitted_time = p.time.split(":");
    this.change_time = {hour: Number(splitted_time[0]), minute: Number(splitted_time[1])};

    this.modalService.open(ChangeProjectionModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  changeProjectionSubmit() {

    this.change_modified_date = this.change_date.day + "-" + this.change_date.month + "-" + this.change_date.year;
    this.change_modified_time = this.change_time.hour + ":" + this.change_time.minute;

    this.cinemaService.updateProjection({old_hall_id: this.change_old_hallID, projection_id : this.change_projection, cinema_id : this.currentCinema.id, hall_id : this.change_hall, film_id : this.change_film , date :  this.change_modified_date, time : this.change_modified_time, discount : this.change_discount})
    .subscribe(data =>
      {
        console.log(data);
        this.currentCinema = data;
      } 
    );

    this.router.navigateByUrl('/cinema-repertoire');
  
  }


  onClickAddFastReservationTickets(h, p, FastTicketsModal) {

    this.fastTickets_hall = h;
    this.fastTickets_projection = p;

    this.modalService.open(FastTicketsModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  addFastTicketsSubmit() {

    this.cinemaService.addFastTickets({hall_id:this.fastTickets_hall.id, projection_id:this.fastTickets_projection.id, fast_tickets_number:this.fast_tickets_number})
    .subscribe(data =>
      {
        console.log(data);
        if(!data){
          alert("You can't create quick reservation tickets because there are no more seats in the hall!");
        } else {
          alert("You have successfully created a quick reservation tickets!");
        }
      }
    );

    this.router.navigateByUrl('/cinema-repertoire');

  }

  
  onClickFastReserve(p) {

    if(this.loggedInUser.roles[0].name!="USER" && this.loggedInUser.roles[0].name!="SYSTEM_ADMIN" && 
    this.loggedInUser.roles[0].name!="CINEMA_ADMIN" && this.loggedInUser.roles[0].name=="FAN_ZONE_ADMIN"){
      alert("Please log in!");
      this.router.navigateByUrl('/login');
    }


    if(this.loggedInUser.roles[0].name=="USER") {

      this.cinemaService.fastReserveTicket({user_id:this.loggedInUser.id, projection_id:p.id})
      .subscribe(data =>
        {
          console.log(data);
          if(!data){
            alert("Projection hass passed or no more tickets for this projection!");
          } else {
            alert("You have reserved a quick ticket successfully!");
          }
        }
      );

      this.router.navigateByUrl('/cinema-repertoire');
    }
    
    if(this.loggedInUser.roles[0].name=="SYSTEM_ADMIN") {
      alert("Only logged-in user can resereve a ticket!");
      this.router.navigateByUrl('/cinema-repertoire');
    }

    if(this.loggedInUser.roles[0].name=="CINEMA_ADMIN") {
      alert("Only logged-in user can resereve a ticket!");
      this.router.navigateByUrl('/cinema-repertoire');
    }

    if(this.loggedInUser.roles[0].name=="FAN_ZONE_ADMIN") {
      alert("Only logged-in user can resereve a ticket!");
      this.router.navigateByUrl('/cinema-repertoire');
    }


  }


}
