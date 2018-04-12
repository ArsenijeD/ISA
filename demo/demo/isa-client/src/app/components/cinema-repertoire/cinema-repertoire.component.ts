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


  constructor(private router : Router, private cinemaService : CinemaService) { }

  ngOnInit() {

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {this.currentCinema = currentCinema;
      console.log(currentCinema);});
  }

}
