import { Component, OnInit } from '@angular/core';
import { AdService } from '../../services/ad.service';

@Component({
  selector: 'app-fan-zone-admin-profile',
  templateUrl: './fan-zone-admin-profile.component.html',
  styleUrls: ['./fan-zone-admin-profile.component.css']
})
export class FanZoneAdminProfileComponent implements OnInit {

  aktivnosti : string[];
  aktivna_tabela : boolean[];
  unconfirmedAds : any;
  myAds : any;

  adName : string;
  adDescription : string;
  adDate : any;
  adImage : any;

  constructor(private adService : AdService) { 

    

  }

  ngOnInit() {
    this.aktivnosti = ["nav-link active", "nav-link", "nav-link", "nav-link"];
    this.aktivna_tabela = [true, false, false, false];

    this.getOnWaitingAds();
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

      // this.cinemaService.getCinemas().subscribe(data=> { this.cinemas = data});
      this.getOnWaitingAds();

    } else if (index == 1) {

      // this.userService.getFanZoneAdmins().subscribe(data=> { this.fanZoneAdmins = data});
      this.getMyAds();

    } else if (index == 2) {

      
      
      // this.userService.getUsers().subscribe(data=> { this.users = data});

    } else if (index == 3) {

      

    }
        
  }

  getOnWaitingAds() {

    this.adService.getAdsByConfirmed(0).subscribe(data=> { this.unconfirmedAds = data; console.log(data)});
  }

  getMyAds() {

    this.adService.getAdsByConfirmed(3).subscribe(data=> { this.myAds = data; console.log(data)});
  }

  approveAd(ad : any) {

    ad.confirmed = 1;
    this.adService.changeAdStatus(ad).subscribe(data =>  this.getOnWaitingAds());
  }

  rejectAd(ad : any) {

    ad.confirmed = 2;
    this.adService.changeAdStatus(ad).subscribe(data =>  this.getOnWaitingAds());
  }

  onSubmit(file : any) {

    
    var img = file.split("\\");

    var img_pass = img[img.length - 1];

    if (img_pass == "")
      img_pass = "defaultOglas.jpg";
    
    this.adService.registerAd({name : this.adName, description : this.adDescription, date : this.adDate.year + "-" + this.adDate.month + "-" + this.adDate.day, image : img_pass, confirmed : 3}).subscribe(data => this.getMyAds());
    this.adName = "";
    this.adDescription="";
    this.adDate="";
    this.adImage = "";

  }

}
