import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TheaterService } from '../../services/theater.service';

@Component({
  selector: 'app-view-theaters',
  templateUrl: './view-theaters.component.html',
  styleUrls: ['./view-theaters.component.css'],
  providers: [TheaterService]
})
export class ViewTheatersComponent implements OnInit {

  private theatersArray: any;


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

}
