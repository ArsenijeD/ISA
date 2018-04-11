import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { RegisterCinemaService } from '../../services/register-cinema.service';

@Component({
  selector: 'app-register-cinema',
  templateUrl: './register-cinema.component.html',
  styleUrls: ['./register-cinema.component.css'],
  providers: [RegisterCinemaService]
})
export class RegisterCinemaComponent implements OnInit {

  constructor(private router : Router, private registerCinemaService : RegisterCinemaService) {


   }

  ngOnInit() {

  }

  onSubmit(cinemaName : string, cinemaAdress : string, cinemaDescription : string) {

    alert("iz komponente: " + cinemaName);
    this.registerCinemaService.registerCinema({name : cinemaName, adress : cinemaAdress, description : cinemaDescription}).subscribe(data => console.log(data));
    //this.router.navigateByUrl('/');
  }

}