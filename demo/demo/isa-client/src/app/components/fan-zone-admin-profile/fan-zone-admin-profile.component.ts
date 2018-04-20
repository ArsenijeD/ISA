import { Component, OnInit } from '@angular/core';
import { AdService } from '../../services/ad.service';
import { Router } from '@angular/router';

import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { BidService } from '../../services/bid.service'
import { AuthServiceService } from '../../services/auth-service.service';

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

  adNameUpdate : string;
  adDescriptionUpdate : string;
  adDateUpdate : any;
  adImageUpdate : any;

  bids : any[][];

  currentUser : any;

  constructor(private authServiceService :AuthServiceService, private adService : AdService, private modalService: NgbModal, private router : Router, private bidService : BidService) { 

    this.currentUser = this.authServiceService.getUser();

  }

  ngOnInit() {
    this.aktivnosti = ["nav-link active", "nav-link", "nav-link", "nav-link"];
    this.aktivna_tabela = [true, false, false, false];

    this.getOnWaitingAds();
    
    this.bids = [];
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

    this.adService.getAdsByConfirmed(0).subscribe(data=> { 
      
      this.unconfirmedAds = data;
      console.log(data);


      
    
    
    });
  }

  getBidsForMyAds(ad_id : number) {

    this.bidService.getBidsForSelectedAd(ad_id).subscribe(data=> { this.bids.push(data); console.log(data); console.log("----")});

  }
  getMyAds() {

    this.adService.getAdsByConfirmed(3).subscribe(data=> { 
      
      this.myAds = data;
      console.log(data);

      for (var _i = 0; _i < this.myAds.length; _i++) {

        this.getBidsForMyAds(this.myAds[_i].id);

      }  


    
    });
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

  deleteAd(adID : any) {

    this.adService.deleteAdById(adID).subscribe(data=> this.getMyAds());

  }

  updateAdModal(updateAd : any, myAd : any) {

    //alert(JSON.stringify(myAd));
    this.adNameUpdate = myAd.name;
    this.adDescriptionUpdate = myAd.description;
    var date_parts : any[] = myAd.date.split("-");
    
    // this.adDateUpdate.year = date_parts[0];
    // this.adDateUpdate.month = date_parts[1];
    // this.adDateUpdate.day = date_parts[2];

    //this.adImageUpdate = myAd.image;

    this.modalService.open(updateAd).result.then((result) => {
      
      
    }, (reason) => {
      
    });

    
    //alert(JSON.stringify(admins))
  }

  onUpdate(file : any, ad : any) {

    //alert(JSON.stringify(this.myAds[index]));
    alert(JSON.stringify(ad));
    var img = file.split("\\");

    var img_pass = img[img.length - 1];

    if (img_pass == "")
      img_pass = "defaultOglas.jpg";

    ad.name = this.adNameUpdate;
    ad.description = this.adDescriptionUpdate;
    ad.date = this.adDateUpdate.year + "-" + this.adDateUpdate.month + "-" + this.adDateUpdate.day;
    ad.image = img_pass;

   
  
    this.adService.updateWholeAd(ad).subscribe(data => this.getMyAds());
  }

}
