import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CinemaService } from '../../services/cinema.service';

@Component({
  selector: 'app-register-cinema',
  templateUrl: './register-cinema.component.html',
  styleUrls: ['./register-cinema.component.css'],
  providers: [CinemaService]
})
export class RegisterCinemaComponent implements OnInit {

  constructor(private router : Router, private cinemaService : CinemaService) {


   }

  ngOnInit() {

  }

  onSubmit(cinemaName : string, cinemaAdress : string, cinemaDescription : string) {

    alert("iz komponente: " + cinemaName);
    this.cinemaService.registerCinema({name : cinemaName, adress : cinemaAdress, description : cinemaDescription}).subscribe(data => console.log(data));

    this.router.navigateByUrl('/adminProfile');

  }

}
