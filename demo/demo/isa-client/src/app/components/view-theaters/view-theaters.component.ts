import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

@Component({
  selector: 'app-view-theaters',
  templateUrl: './view-theaters.component.html',
  styleUrls: ['./view-theaters.component.css']
})
export class ViewTheatersComponent implements OnInit {

  private theatersArray: any;
  private selectedTheater: any;


  constructor(private router : Router, private theaterService : TheaterService) {

  }

  ngOnInit() {

    this.theaterService.getTheaters()
    .subscribe(
      data=> 
      {this.theatersArray = data;
        
        console.log(data);
      }
    );

  }

  onClickTheaterDetails(Theater:any) : void {
    this.selectedTheater = Theater;

    this.theaterService.selectTheater(Theater);

    this.theaterService.currentTheater.subscribe(
      currentTheater => 
      {
      console.log("viev-cinema 2: " +  currentTheater);});

    this.router.navigateByUrl('/theater-repertoire');
  }

}


