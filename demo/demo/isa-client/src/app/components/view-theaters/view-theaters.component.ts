import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

import { GeocoderService } from '../../services/geocoder.service';

import { AuthServiceService} from '../../services/auth-service.service';

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

  private loggedInUser : any;
  private isAdminArray : boolean[] = [];

  constructor(private router : Router, private theaterService : TheaterService, private geocoderService : GeocoderService, private authService:AuthServiceService) {

  }

  ngOnInit() {

    this.loggedInUser = this.authService.getUser();
    console.log(this.loggedInUser);

    this.theaterService.getTheaters()
    .subscribe(
      data=> 
      {
        this.theatersArray = data;
        
        console.log(data);

        for(let i=0; i<data.length; i++){
          this.getCords(data[i].adress);
          console.log("----");
          console.log(data[i].adress);
        }


        // ZBOG SAKRIVANJA DUGMETA DETAILS

        for (let i = 0; i < this.theatersArray.length; i++) {    
          this.isAdminArray.push(true);
          for (let j = 0; j < this.theatersArray[i].admins.length; j++) {
            if(this.theatersArray[i].admins[j].id==this.loggedInUser.id){
              console.log("nasao admina pozorista!");
                this.isAdminArray[i] = false;
  
              } else {
                console.log("nije nasao admina pozorista!")
                this.isAdminArray[i] = true;
            }
          }
            
        }

      }
    );

  }

  onClickTheaterRepertoar(Theater:any) : void {
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

  onClickTheaterDetails(Theater:any) : void {

    this.selectedTheater = Theater;

    this.theaterService.selectTheater(Theater);

    this.theaterService.currentTheater.subscribe(
      currentTheater => 
      {
      console.log("Current Theater: " +  currentTheater);});

    this.router.navigateByUrl('/theater-details');

  }

}


