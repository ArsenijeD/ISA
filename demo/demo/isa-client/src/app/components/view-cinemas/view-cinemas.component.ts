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

  latitude = 51.678418;
  longitude = 7.809007;
  

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

  onClickCinemaDetails(Cinema:any) : void {
    this.selectedCinema = Cinema;
    console.log("view-cineam: " + Cinema);  
    this.cinemaService.selectCinema(Cinema);

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {
      console.log("viev-cinema 2: " +  currentCinema);});

    this.router.navigateByUrl('/cinema-repertoire');
  }

}
 


