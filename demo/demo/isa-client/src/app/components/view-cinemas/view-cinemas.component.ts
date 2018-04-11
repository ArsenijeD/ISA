import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';


@Component({
  selector: 'app-view-cinemas',
  templateUrl: './view-cinemas.component.html',
  styleUrls: ['./view-cinemas.component.css']
})
export class ViewCinemasComponent implements OnInit {

  private cinemasArray: any;

  private selectedCinema: any;

  private editButtonHidden: boolean = true;


  constructor(private router : Router, private cinemaService : CinemaService) {

  }

  ngOnInit() {

    this.cinemaService.getCinemas()
      .subscribe(
        data=> 
        {this.cinemasArray = data;
          
          console.log(data);
        }
      );


  }

}
