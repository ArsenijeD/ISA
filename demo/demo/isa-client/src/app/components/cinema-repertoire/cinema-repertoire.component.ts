import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

@Component({
  selector: 'app-cinema-repertoire',
  templateUrl: './cinema-repertoire.component.html',
  styleUrls: ['./cinema-repertoire.component.css'],
})
export class CinemaRepertoireComponent implements OnInit {

  private currentCinema: any;
  private hallsArray : any;


  constructor(private router : Router, private cinemaService : CinemaService) { }

  ngOnInit() {

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {this.currentCinema = currentCinema;
      console.log(currentCinema);});


      this.cinemaService.getHallsByCinemaID(this.currentCinema.id)
      .subscribe(
        data=> 
        {this.hallsArray = data;
          
          console.log(data);
          console.log(this.currentCinema.id);
        }
      );
    

  }




}
