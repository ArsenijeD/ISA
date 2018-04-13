import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

@Component({
  selector: 'app-theater-repertoire',
  templateUrl: './theater-repertoire.component.html',
  styleUrls: ['./theater-repertoire.component.css']
})
export class TheaterRepertoireComponent implements OnInit {

  private currentTheater: any;

  constructor(private router : Router, private theaterService : TheaterService) { }

  ngOnInit() {

    this.theaterService.currentTheater.subscribe(
      currentTheater => 
      {this.currentTheater = currentTheater;
      console.log(currentTheater);});
  }

}
