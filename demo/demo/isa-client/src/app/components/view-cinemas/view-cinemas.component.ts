import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../../services/cinema.service';

import { GeocoderService } from '../../services/geocoder.service';

import { AuthServiceService} from '../../services/auth-service.service';


@Component({
  selector: 'app-view-cinemas',
  templateUrl: './view-cinemas.component.html',
  styleUrls: ['./view-cinemas.component.css']
})
export class ViewCinemasComponent implements OnInit {

  private cinemasArray: any;
  private selectedCinema : any;

  private loggedInUser : any;
  private isAdminArray : boolean[] = [];

  private latitudes : any[] = [];
  private longitudes : any[] = []; 
 

  constructor(private router : Router, private cinemaService : CinemaService, private geocoderService : GeocoderService, private authService:AuthServiceService) {

  
  }

  ngOnInit() {

    this.loggedInUser = this.authService.getUser();
    console.log(this.loggedInUser);

    this.cinemaService.getCinemas()
      .subscribe(
        data=> 
        {
          this.cinemasArray = data;
          
          console.log(this.cinemasArray);

          for(let i=0; i<data.length; i++){
            this.getCords(data[i].adress);
            console.log("----");
            console.log(data[i].adress);
          }


          // OVO JE ZA SAKRIVANJA DUGMETA DETAILS
          
          // for (let i = 0; i < this.cinemasArray.length; i++) {   
          //   this.isAdminArray.push(true);
          //   for (let j = 0; j < this.cinemasArray[i].admins.length; j++) {
          //     if(this.cinemasArray[i].admins[j].id==this.loggedInUser.id){
          //       console.log("nasao admina pozorista!");
          //         this.isAdminArray[i] = false;
    
          //       } else {
          //         console.log("nije nasao admina pozorista!")
          //         this.isAdminArray[i] = true;
          //     }
          //   }
              
          // }
            
        }
        
      );

     

      //this.getLatLng("Trifkovicev trg 6, Novi Sad, Srbija");
      //this.getCords("277 Bedford Avenue, Brooklyn, NY 11211, USA");
      //this.getAddress();
      

  }

  
  onClickCinemaRepertoar(Cinema:any) : void {
    this.selectedCinema = Cinema;
    console.log("Cinema: " + Cinema);  
    this.cinemaService.selectCinema(Cinema);

    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {
      console.log("Current cinema: " +  currentCinema);
      }
    );

    this.router.navigateByUrl('/cinema-repertoire');
  }

  
  getCords(address : string) {

    this.geocoderService.getlatlng(address).subscribe(data=> {  this.latitudes.push(data.results[0].geometry.location.lat); this.longitudes.push(data.results[0].geometry.location.lng);});
    
  }


  onClickCinemaDetails(Cinema : any) :void {
    this.selectedCinema = Cinema; 
    this.cinemaService.selectCinema(Cinema);
    this.cinemaService.currentCinema.subscribe(
      currentCinema => 
      {
      console.log("Current cinema: " +  currentCinema);
      }
    );

    this.router.navigateByUrl('/cinema-details');

  }
}
 


