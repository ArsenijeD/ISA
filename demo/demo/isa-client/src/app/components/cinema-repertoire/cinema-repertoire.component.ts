import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


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
  
 

  constructor(private router : Router, private cinemaService : CinemaService, private modalService: NgbModal) { }


  ngOnInit() {

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {
        this.currentCinema = currentCinema;
        console.log(currentCinema);
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

  

}
