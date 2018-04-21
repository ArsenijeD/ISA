import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AgmCoreModule, MapsAPILoader } from '@agm/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { GeocoderService } from '../../services/geocoder.service';

@Component({
  selector: 'app-theater-details',
  templateUrl: './theater-details.component.html',
  styleUrls: ['./theater-details.component.css']
})
export class TheaterDetailsComponent implements OnInit {


  private currentTheater: any;

  private performancesArray : any[] = [];

  private aktivnosti : string[];
  private aktivna_tabela : boolean[];


  private theater_name : any;
  private theater_description : any;
  private theater_address : any;


  private performance_name : any;
  private performance_genre : any;
  private performance_description : any;
  private performance_duration : any;
  private performance_averageRating : any;
  private performance_price : any;


  private change_performance_id : any;
  private change_performance_name : any;
  private change_performance_genre : any;
  private change_performance_description : any;
  private change_performance_duration : any;
  private change_performance_averageRating : any;
  private change_performance_price : any;

  private can_delete : boolean;

  private stage_number : any;
  private number_OfChairs : any;


  private latitudes : any[] = [];
  private longitudes : any[] = []; 

  @ViewChild('theaterAdress') searchElement : ElementRef;

  constructor(private mapsAPILoader : MapsAPILoader, private ngZone : NgZone, private modalService: NgbModal, private router : Router, private theaterService : TheaterService, private geocoderService : GeocoderService) { }

  ngOnInit() {

    this.aktivnosti = ["nav-link active", "nav-link"];
    this.aktivna_tabela = [true, false];

    this.theaterService.currentTheater.subscribe(
      data => 
      {
        this.currentTheater = data;
        console.log(data);

        this.getCords(data.adress);
        console.log("----");
        console.log(data.adress);
      }
    );

  }

  getCords(address : string) {

    this.geocoderService.getlatlng(address).subscribe(data=> {  this.latitudes.push(data.results[0].geometry.location.lat); this.longitudes.push(data.results[0].geometry.location.lng);});
    
  }


  promeniAktivnost(index: number) {

    for (var i = 0; i < this.aktivnosti.length; i++) {
      this.aktivnosti[i] = "nav-link";
      
    }

    for (var j = 0; j < this.aktivna_tabela.length; j++) {
      this.aktivna_tabela[j] = false;
      
    }
  
    this.aktivnosti[index] = "nav-link active";
    this.aktivna_tabela[index] = true;

    // if (index == 0) {
    //   this.cinemaService.currentCinema.subscribe(
    //     currentCinema => 
    //     {
    //       this.currentCinema = currentCinema;
    //       console.log(currentCinema);
    //     }
    //   );

    // }

    if (index == 1) {

      this.theaterService.getPerformances().subscribe(
        data=> 
        {
          this.performancesArray = data;    
          console.log(data);
        }
      );

    }

  }



  onClickChangeTheater(theater, ChangeTheaterModal) {

    this.theater_name = this.currentTheater.name;
    this.theater_description = this.currentTheater.description;
    this.theater_address = this.currentTheater.adress;


    this.modalService.open(ChangeTheaterModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }

  changeTheaterSubmit() {

    this.theaterService.changeTheater({id : this.currentTheater.id, name : this.theater_name, adress : this.theater_address , description :  this.theater_description})
    .subscribe(data =>
      {

        if(data==null){

          alert("An error has occurred!");

        } else {

          console.log(data);
          this.currentTheater = data;
          alert("Succesfully changed theater!");

        }
        
      } 
    );

    this.router.navigateByUrl('/theater-details');

  }



  onClickAddPerformance(AddPerformanceModal) {

    this.modalService.open(AddPerformanceModal).result.then((result) => {
      
    }, (reason) => {
      
    });

  }


  addPerformanceSubmit() {

    this.theaterService.registerPerformance({name:this.performance_name, duration:this.performance_duration, description:this.performance_description, genre:this.performance_genre, averageRating:0, price:this.performance_price})
    .subscribe(data => {
        console.log(data);

        this.theaterService.getPerformances().subscribe(
          data=> 
          {

            if(data==null){

              alert("An error has occurred!");
    
            } else {
    
              this.performancesArray = data;    
              console.log(data);
              alert("Succesfully added performance!");

            }

         
          }
        );

      } 
    );

    this.router.navigateByUrl('/theater-details');

  }


  onClickChangePerformance(performance, changePerformanceModal) {

    this.change_performance_id = performance.id;
    this.change_performance_name = performance.name;
    this.change_performance_description = performance.description;
    this.change_performance_duration = performance.duration;
    this.change_performance_genre = performance.genre;
    this.change_performance_price = performance.price;
    this.change_performance_averageRating = performance.averageRating;

    this.modalService.open(changePerformanceModal).result.then((result) => {
      
    }, (reason) => {
      
    });

  }

  changePerformanceSubmit() {

    this.theaterService.updatePerformance({id:this.change_performance_id, name:this.change_performance_name, duration:this.change_performance_duration, description:this.change_performance_description, genre:this.change_performance_genre, price:this.change_performance_price})
    .subscribe(data => {
        console.log(data);

        this.theaterService.getPerformances().subscribe(
          data=> 
          {

            if(data==null){

              alert("An error has occurred!");
    
            } else {
     
              this.performancesArray = data;    
              console.log(data);
              alert("Succesfully changed performance!");
              
            }

          }
        );

      } 
    );

    this.router.navigateByUrl('/theater-details');

  }

  onClickDeletePerformance(performance:any) : void {
    
    this.theaterService.deletePerformance(performance.id)
    .subscribe(data =>
      {
        console.log(data);

        this.can_delete = data;
        if(this.can_delete){

          this.theaterService.getPerformances().subscribe(
            data=> 
            {
              this.performancesArray = data;    
              console.log(data);
              alert("Succesfully deleted performance!");
            }
          );

        } else {

          alert("You can't delete this performance, because there is presentation for this performance!");

        }
        
      }
    );

    this.router.navigateByUrl('/theater-details');
  
  }


  onClickAddStage(AddStageModal) {

    this.modalService.open(AddStageModal).result.then((result) => {
      
    }, (reason) => {
      
    });

  }

  addStageSubmit() {

    this.theaterService.addStage({theater_id:this.currentTheater.id, number:this.stage_number, numberOfSeats:this.number_OfChairs})
    .subscribe(data => {

      if(data==null){

        alert("An error has occurred!");

      } else {

        console.log(data);
        this.currentTheater = data;
        alert("Succesfully added stage!");
        
      }

      } 
    );

    this.router.navigateByUrl('/theater-details');

  }


  onClickDeleteStage(theaterID:any, stageID:any) : void {
    this.theaterService.deleteStage(theaterID, stageID)
    .subscribe(data =>
      {

        if(data==null){

          alert("An error has occurred!");

        } else {

          console.log(data);
          this.currentTheater = data;
          alert("Succesfully deleted stage!");
          
        }

      }
    );

    this.router.navigateByUrl('/theater-details');

  }



}
