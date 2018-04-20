import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { AuthServiceService} from '../../services/auth-service.service';

@Component({
  selector: 'app-theater-repertoire',
  templateUrl: './theater-repertoire.component.html',
  styleUrls: ['./theater-repertoire.component.css']
})
export class TheaterRepertoireComponent implements OnInit {

  private currentTheater: any;


  private stagesArray : any;

  private performancesArray : any[] = [];

  private performance : any;
  private stage : any;
  private date : any;
  private modified_date : any;
  private time = {hour: 18, minute: 30};
  private modified_time : any;
  private discount : any;



  private change_presentation : any;
  private change_performance : any;
  private change_stage : any;
  private change_date : any;
  private change_modified_date : any;
  private change_time : any;
  private change_modified_time : any;
  private change_discount : any;

  private change_old_stageID : any;

  private loggedInUser : any;
  private isAdmin = true;

  constructor(private router : Router, private theaterService : TheaterService, private modalService: NgbModal, private authService:AuthServiceService) { }

  ngOnInit() {

    this.theaterService.currentTheater.subscribe(
      currentTheater => 
      {
        this.currentTheater = currentTheater;
        console.log(currentTheater);});


        for (let i = 0; i < this.currentTheater.admins.length; i++) {
          if(this.currentTheater.admins[i].id==this.loggedInUser.id){
            console.log("nasao admina pozorista!");
              this.isAdmin = false;
          } else {
            console.log("Nije nasao admina pozorista!");
            this.isAdmin = true;
          }
        }


        this.theaterService.getPerformances()
        .subscribe(
          data=> 
          {this.performancesArray = data;    
            console.log(data);
          }
        );

  }


  onClickDeletePresentation(theaterID:any, stageID:any, presentationID:any) : void {
    
    this.theaterService.deletePresentationById(theaterID, stageID, presentationID)
    .subscribe(data =>
      {
        console.log(data);
        this.currentTheater = data;
      }
    );

    this.router.navigateByUrl('/theater-repertoire');
  
  }

  //modalni dijalozi

  onClickAddPresentation(AddPresentationModal) {
    this.modalService.open(AddPresentationModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  addPresentationSubmit() {

    this.modified_date = this.date.day + "-" + this.date.month + "-" + this.date.year;
    this.modified_time = this.time.hour + ":" + this.time.minute;

    this.theaterService.registerPresentation({theater_id: this.currentTheater.id, stage_id : this.stage, performance_id : this.performance , date :  this.modified_date, time : this.modified_time, discount : this.discount})
    .subscribe(data =>
      {
        console.log(data);
        this.currentTheater = data;
      } 
    );

    this.router.navigateByUrl('/theater-repertoire');
  
  }


  onClickChangePresentation(s, p, ChangePresentationModal) {

    this.change_old_stageID = s.id;

    this.change_presentation = p.id;

    this.change_performance = p.performance.id;
    this.change_stage = s.id;
    this.change_discount = p.discount;
    let splitted_date = p.date.split("-");
    this.change_date = {year: Number(splitted_date[2]), month: Number(splitted_date[1]), day:Number(splitted_date[0])};
    let splitted_time = p.time.split(":");
    this.change_time = {hour: Number(splitted_time[0]), minute: Number(splitted_time[1])};

    this.modalService.open(ChangePresentationModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  changePresentationSubmit() {

    this.change_modified_date = this.change_date.day + "-" + this.change_date.month + "-" + this.change_date.year;
    this.change_modified_time = this.change_time.hour + ":" + this.change_time.minute;

    this.theaterService.updatePresentation({old_stage_id: this.change_old_stageID, presentation_id : this.change_presentation, theater_id : this.currentTheater.id, stage_id : this.change_stage, performance_id : this.change_performance , date :  this.change_modified_date, time : this.change_modified_time, discount : this.change_discount})
    .subscribe(data =>
      {
        console.log(data);
        this.currentTheater = data;
      } 
    );

    this.router.navigateByUrl('/theater-repertoire');
  
  }

  

}
