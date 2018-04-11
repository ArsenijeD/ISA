import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  aktivnosti : string[];
  aktivna_tabela : boolean[];

  constructor(private router : Router) { }

  ngOnInit() {

    this.aktivnosti = ["active", "", "", ""];
    this.aktivna_tabela = [true, false, false, false];
  }

  promeniAktivnost(index: number) {

    for (var i = 0; i < this.aktivnosti.length; i++) {
      this.aktivnosti[i] = "";
      
    }

    for (var j = 0; j < this.aktivna_tabela.length; j++) {
      this.aktivna_tabela[j] = false;
      
    }
  
    this.aktivnosti[index] = "active";
    this.aktivna_tabela[index] = true;

  }
}
