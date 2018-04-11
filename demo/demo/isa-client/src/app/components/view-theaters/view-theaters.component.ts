import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-theaters',
  templateUrl: './view-theaters.component.html',
  styleUrls: ['./view-theaters.component.css']
})
export class ViewTheatersComponent implements OnInit {

  constructor(private router : Router) {

  }

  ngOnInit() {
  }

}
