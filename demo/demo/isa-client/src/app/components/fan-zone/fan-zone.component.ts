import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { AdService } from '../../services/ad.service';

import {} from '@types/googlemaps';
import { AgmCoreModule, MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-fan-zone',
  templateUrl: './fan-zone.component.html',
  styleUrls: ['./fan-zone.component.css']
})
export class FanZoneComponent implements OnInit {

  adName : string;
  adDescription : string;
  adDate : any;
  adImage : any;

  myAds : any;



  constructor(private adService : AdService) { }

  ngOnInit() {

    this.getMyAds();
    
  }

  onSubmit(file : any) {

    
    var img = file.split("\\");

    var img_pass = img[img.length - 1];

    if (img_pass == "")
      img_pass = "defaultOglas.jpg";
    
    this.adService.registerAd({name : this.adName, description : this.adDescription, date : this.adDate.year + "-" + this.adDate.month + "-" + this.adDate.day, image : img_pass, confirmed : 0}).subscribe(data => this.getMyAds());
    this.adName = "";
    this.adDescription="";
    this.adDate="";
    this.adImage = "";

  }

  getMyAds() {

    this.adService.getAdsOfCurrentUser().subscribe(data=> { this.myAds = data});
  }

  
}


