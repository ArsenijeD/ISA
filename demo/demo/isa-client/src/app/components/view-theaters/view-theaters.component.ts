import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

import { GeocoderService } from '../../services/geocoder.service';

@Component({
  selector: 'app-view-theaters',
  templateUrl: './view-theaters.component.html',
  styleUrls: ['./view-theaters.component.css']
})
export class ViewTheatersComponent implements OnInit {

  private theatersArray: any;
  private selectedTheater: any;

  private latitudes : any[] = [];
  private longitudes : any[] = [];

  constructor(private router : Router, private theaterService : TheaterService, private geocoderService : GeocoderService) {

  }

  ngOnInit() {

    this.theaterService.getTheaters()
    .subscribe(
      data=> 
      {this.theatersArray = data;
        
        console.log(data);

        for(let i=0; i<data.length; i++){
          this.getCords(data[i].adress);
          console.log("----");
          console.log(data[i].adress);
        }
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

  getCords(address : string) {

    this.geocoderService.getlatlng(address).subscribe(data=> {  this.latitudes.push(data.results[0].geometry.location.lat); this.longitudes.push(data.results[0].geometry.location.lng);});
    
  }

}


