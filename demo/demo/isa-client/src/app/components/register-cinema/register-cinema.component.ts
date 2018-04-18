import { Component, OnInit, ViewChild, ElementRef, NgZone} from '@angular/core';
import { Router } from '@angular/router';

import { CinemaService } from '../../services/cinema.service';

import {} from '@types/googlemaps';
import { AgmCoreModule, MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-register-cinema',
  templateUrl: './register-cinema.component.html',
  styleUrls: ['./register-cinema.component.css'],
  providers: [CinemaService]
})
export class RegisterCinemaComponent implements OnInit {

  @ViewChild('cinemaAdress') searchElement : ElementRef;

  constructor(private mapsAPILoader : MapsAPILoader, private ngZone : NgZone, private router : Router, private cinemaService : CinemaService) {


   }

  ngOnInit() {

    this.mapsAPILoader.load().then(

      () => {

        let autocomplete = new google.maps.places.Autocomplete(this.searchElement.nativeElement, {types : ["address"]});

        autocomplete.addListener("place_change", () => {

          this.ngZone.run(() => {

            let place : google.maps.places.PlaceResult = autocomplete.getPlace();

            if (place.geometry == undefined || place.geometry == null)
            return;
          });
        })

      }
  );
  }

  // onSubmit(cinemaName : string, cinemaAdress : string, cinemaDescription : string) {

  //   alert("iz komponente: " + cinemaName);
  //   this.cinemaService.registerCinema({name : cinemaName, adress : cinemaAdress, description : cinemaDescription}).subscribe(data => console.log(data));

  //   this.router.navigateByUrl('/adminProfile');

  // }

  onSubmit(cinemaName : string, cinemaAdress : string, cinemaDescription : string) {

    alert("iz komponente: " + cinemaName);
    this.cinemaService.registerCinema({name : cinemaName, adress : cinemaAdress, description : cinemaDescription}).subscribe(data => console.log(data));
  

    // this.cinemaService.getCinemas().subscribe(data=> { this.cinemas = data});
    // this.promeniAktivnost(0);
  }

}
