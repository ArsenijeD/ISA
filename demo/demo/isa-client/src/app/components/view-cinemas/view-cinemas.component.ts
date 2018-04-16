import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

import { GeocoderService } from '../../services/geocoder.service';


@Component({
  selector: 'app-view-cinemas',
  templateUrl: './view-cinemas.component.html',
  styleUrls: ['./view-cinemas.component.css']
})
export class ViewCinemasComponent implements OnInit {

  private cinemasArray: any;
  private selectedCinema: any;

  private latitudes : any[] = [];
  private longitudes : any[] = []; 
 
  
 

  constructor(private router : Router, private cinemaService : CinemaService, private geocoderService : GeocoderService) {

    

      
    
    
  }

  ngOnInit() {

    this.cinemaService.getCinemas()
      .subscribe(
        data=> 
        {this.cinemasArray = data;
          
          console.log(this.cinemasArray);

          for(let i=0; i<data.length; i++){
            this.getCords(data[i].adress);
            console.log("----");
            console.log(data[i].adress);
          }
        }
      );

    
      //this.getLatLng("Trifkovicev trg 6, Novi Sad, Srbija");
      //this.getCords("277 Bedford Avenue, Brooklyn, NY 11211, USA");
      //this.getAddress();
      

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

  
  getCords(address : string) {

    this.geocoderService.getlatlng(address).subscribe(data=> {  this.latitudes.push(data.results[0].geometry.location.lat); this.longitudes.push(data.results[0].geometry.location.lng);});
    
  }

}
 


