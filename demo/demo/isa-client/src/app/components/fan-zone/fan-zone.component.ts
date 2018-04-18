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
  adDate : string;
  adImage : string;



  constructor( private adService : AdService) { }

  ngOnInit() {


    
  }

  onSubmit() {

    
    this.adService.registerAd({name : this.adName, description : this.adDescription, date : this.adDate, image : this.adImage, confirmed : false}).subscribe(data => console.log(data));
    this.adName = "";
    this.adDescription="";
    this.adDate="";
    this.adImage="";
  }

}
