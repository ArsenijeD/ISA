import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CinemaService } from '../../services/cinema.service';
import { UserService } from '../../services/user.service';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-admin-profile-page',
  templateUrl: './admin-profile-page.component.html',
  styleUrls: ['./admin-profile-page.component.css'],
  providers: [CinemaService, UserService]
})

export class AdminProfilePageComponent implements OnInit {

  cinemas : any;
  aktivnosti : string[];
  aktivna_tabela : boolean[];
  cinemaAdmins : any;
  users : any;
  fanZoneAdmins : any;

  constructor(private router : Router, private cinemaService : CinemaService, private userService : UserService, private modalService: NgbModal) { }

  ngOnInit() {

    this.aktivnosti = ["nav-link active", "nav-link", "nav-link", "nav-link"];
    this.aktivna_tabela = [true, false, false, false];

    this.cinemaService.getCinemas().subscribe(data=> { this.cinemas = data});
    // this.userService.getUsers().subscribe(data=> { this.users = data});
  }

  promeniAktivnost(index: number) {

    
    
    for (var i = 0; i < this.aktivnosti.length; i++) {
      this.aktivnosti[i] = "nav-link";
      
    }

    for (var j = 0; j < this.aktivna_tabela.length; j++) {
      this.aktivna_tabela[j] = false;
      
    }
  
    this.aktivnosti[index] = "nav-link active";
    this.aktivna_tabela[index] = true;

    
    if (index == 0) {

      this.cinemaService.getCinemas().subscribe(data=> { this.cinemas = data});

    } else if (index == 2) {

      this.userService.getFanZoneAdmins().subscribe(data=> { this.fanZoneAdmins = data});
      

    } else if (index == 3) {

      
      this.userService.getUsers().subscribe(data=> { this.users = data});

    } 
      
      
  }


  //modalni dijalozi
  openCinema(contentCinema) {
    this.modalService.open(contentCinema).result.then((result) => {
      
    }, (reason) => {
      
    });
    
    
  }

  // private getDismissReason(reason: any): string {
  //   if (reason === ModalDismissReasons.ESC) {
  //     return 'by pressing ESC';
  //   } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
  //     return 'by clicking on a backdrop';
  //   } else {
  //     return  `with: ${reason}`;
  //   }
  // }

  openAdmins(contentAdmins) {
    this.modalService.open(contentAdmins).result.then((result) => {
      
    }, (reason) => {
      
    });

    
    //alert(JSON.stringify(admins))
  }

  

  // private getDismissReason(reason: any): string {
  //   if (reason === ModalDismissReasons.ESC) {
  //     return 'by pressing ESC';
  //   } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
  //     return 'by clicking on a backdrop';
  //   } else {
  //     return  `with: ${reason}`;
  //   }
  // }

  openCinemaModal(contentCinemaModal) {
    this.modalService.open(contentCinemaModal).result.then((result) => {
      
    }, (reason) => {
      
    });
    
    
  }
  //------------------------------

  
  onSubmit(cinemaName : string, cinemaAdress : string, cinemaDescription : string) {

    alert("iz komponente: " + cinemaName);
    this.cinemaService.registerCinema({name : cinemaName, adress : cinemaAdress, description : cinemaDescription}).subscribe(data => console.log(data));
  

    this.cinemaService.getCinemas().subscribe(data=> { this.cinemas = data});
    this.promeniAktivnost(0);
  }

  promoteToCinemaAdmin(user : any, cinema : any) {

      cinema.admins.push(user);
      user.role.name = "cinema_admin";
      user.role.role_id = 2;
      
      this.cinemaService.updateCinema(cinema).subscribe(data => console.log(data));
      this.userService.updateUserRole(user).subscribe(data => console.log(data));

      this.userService.getUsers().subscribe(data=> { this.users = data});
      this.promeniAktivnost(3);
  }

  promoteToFanZoneAdmin(user : any) {

    user.role.name = "fan_zone_admin";
    user.role.role_id = 3;
    this.userService.updateUserRole(user).subscribe(data => console.log(data));

    this.userService.getUsers().subscribe(data=> { this.users = data});
    this.promeniAktivnost(3);
  }
}
