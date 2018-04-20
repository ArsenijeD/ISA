import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AgmCoreModule, MapsAPILoader } from '@agm/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { GeocoderService } from '../../services/geocoder.service';


@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.css']
})
export class CinemaDetailsComponent implements OnInit {

  private currentCinema: any;

  private filmsArray : any[] = [];

  private aktivnosti : string[];
  private aktivna_tabela : boolean[];


  private cinema_name : any;
  private cinema_description : any;
  private cinema_address : any;


  private film_name : any;
  private film_genre : any;
  private film_description : any;
  private film_duration : any;
  private film_averageRating : any;
  private film_price : any;


  private change_film_id : any;
  private change_film_name : any;
  private change_film_genre : any;
  private change_film_description : any;
  private change_film_duration : any;
  private change_film_averageRating : any;
  private change_film_price : any;

  private can_delete : boolean;

  private hall_number : any;
  private number_OfSeats : any;


  private latitudes : any[] = [];
  private longitudes : any[] = []; 

  @ViewChild('cinemaAdress') searchElement : ElementRef;

  constructor(private mapsAPILoader : MapsAPILoader, private ngZone : NgZone, private modalService: NgbModal, private router : Router, private cinemaService : CinemaService, private geocoderService : GeocoderService) { }

  ngOnInit() {

    this.aktivnosti = ["nav-link active", "nav-link"];
    this.aktivna_tabela = [true, false];

    this.cinemaService.currentCinema.subscribe(
      data => 
      {
        this.currentCinema = data;
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

      this.cinemaService.getFilms().subscribe(
        data=> 
        {
          this.filmsArray = data;    
          console.log(data);
        }
      );

    }

  }


  onClickChangeCinema(cinema, ChangeCinemaModal) {

    this.cinema_name = this.currentCinema.name;
    this.cinema_description = this.currentCinema.description;
    this.cinema_address = this.currentCinema.adress;


    this.modalService.open(ChangeCinemaModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
  }


  changeCinemaSubmit() {

    this.cinemaService.changeCinema({id : this.currentCinema.id, name : this.cinema_name, adress : this.cinema_address , description :  this.cinema_description})
    .subscribe(data =>
      {
        console.log(data);
        this.currentCinema = data;
      } 
    );

    this.router.navigateByUrl('/cinema-details');

  }


  onClickAddFilm(AddFilmModal) {

    this.modalService.open(AddFilmModal).result.then((result) => {
      
    }, (reason) => {
      
    });

  }


  addFilmSubmit() {

    this.cinemaService.registerFilm({name:this.film_name, duration:this.film_duration, description:this.film_description, genre:this.film_genre, averageRating:this.film_averageRating, price:this.film_price})
    .subscribe(data => {
        console.log(data);

        this.cinemaService.getFilms().subscribe(
          data=> 
          {
            this.filmsArray = data;    
            console.log(data);
          }
        );

      } 
    );

    this.router.navigateByUrl('/cinema-details');

  }

  
  onClickChangeFilm(film, changeFilmModal) {

    this.change_film_id = film.id;
    this.change_film_name = film.name;
    this.change_film_description = film.description;
    this.change_film_duration = film.duration;
    this.change_film_genre = film.genre;
    this.change_film_price = film.price;
    this.change_film_averageRating = film.averageRating;

    this.modalService.open(changeFilmModal).result.then((result) => {
      
    }, (reason) => {
      
    });


  }


  changeFilmSubmit() {

    this.cinemaService.updateFilm({id:this.change_film_id, name:this.change_film_name, duration:this.change_film_duration, description:this.change_film_description, genre:this.change_film_genre, averageRating:this.change_film_averageRating, price:this.change_film_price})
    .subscribe(data => {
        console.log(data);

        this.cinemaService.getFilms().subscribe(
          data=> 
          {
            this.filmsArray = data;    
            console.log(data);
          }
        );

      } 
    );


    this.router.navigateByUrl('/cinema-details');

  }


  onClickDeleteFilm(film:any) : void {
    
    this.cinemaService.deleteFilm(film.id)
    .subscribe(data =>
      {
        console.log(data);

        this.can_delete = data;
        if(this.can_delete){

          this.cinemaService.getFilms().subscribe(
            data=> 
            {
              this.filmsArray = data;    
              console.log(data);
            }
          );

        } else {

          alert("You can't delete this film, because there is projection for this film!");

        }
        
      }
    );

    this.router.navigateByUrl('/cinema-details');
  
  }


  onClickAddHall(AddHallModal) {

    this.modalService.open(AddHallModal).result.then((result) => {
      
    }, (reason) => {
      
    });

  }

  addHallSubmit() {

    this.cinemaService.addHall({cinema_id:this.currentCinema.id, number:this.hall_number, numberOfSeats:this.number_OfSeats})
    .subscribe(data => {
        console.log(data);

        this.currentCinema = data;

      } 
    );

    this.router.navigateByUrl('/cinema-details');

  }


  onClickDeleteHall(cinemaID:any, hallID:any) : void {
    this.cinemaService.deleteHall(cinemaID, hallID)
    .subscribe(data =>
      {
        console.log(data);
        this.currentCinema = data;
      }
    );

    this.router.navigateByUrl('/cinema-details');

  }



}
