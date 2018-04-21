import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { AdService } from '../../services/ad.service';
import { BidService } from '../../services/bid.service';
import { NotificationService } from '../../services/notification.service';

import {} from '@types/googlemaps';
import { AgmCoreModule, MapsAPILoader } from '@agm/core';

import { AuthServiceService } from '../../services/auth-service.service';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ViewContainerRef } from '@angular/core';
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

  myAds : any[];
  myAdsBids : any;
  officialAds : any[];
  officialAdsBids : any;
  otherUsersAds : any[];
  otherUsersAdsBids : any;

  currentUser : any;
 
  myNotifications : any;

  constructor(private toastr: ToastsManager,private vcr: ViewContainerRef, private notificationService : NotificationService, private authServiceService :AuthServiceService, private adService : AdService, private bidService : BidService) { 
    this.toastr.setRootViewContainerRef(vcr);
    this.currentUser = this.authServiceService.getUser();

  }

  ngOnInit() {

    this.myAds = [];
    this.myAdsBids = new Map();
    this.officialAds = [];
    this.officialAdsBids = new Map();
    this.otherUsersAds = [];
    this.otherUsersAdsBids = new Map();
    this.getMyAds();
    this.getOfficialAds();
    this.getOtherUsersAds();
    
    
   this.getNotificationsForCurrentUser();
   
  }

  showNotification(msg : string) {
    this.toastr.success(msg, 'Notification!');
  }

  showError() {
    this.toastr.error('This is not good!', 'Oops!');
  }
  ngAfterViewInit() {

    
  }

  onSubmit(file : any) {

    
    var img = file.split("\\");

    var img_pass = img[img.length - 1];

    if (img_pass == "")
      img_pass = "defaultOglas.jpg";
    
    this.adService.registerAd({name : this.adName, description : this.adDescription, date : this.adDate.year + "-" + this.adDate.month + "-" + this.adDate.day, image : img_pass, confirmed : 0, user : this.currentUser}).subscribe(data => this.getMyAds());
    this.adName = "";
    this.adDescription="";
    this.adDate="";
    this.adImage = "";

  }

  getMyAds() {

    this.adService.getAdsOfCurrentUser(this.currentUser.id).subscribe(data=> {
      
      this.myAds = data;
      console.log(this.myAds);  
    
      

      for (var _i = 0; _i < this.myAds.length; _i++) {

        this.getBidsForSelectedAd(this.myAds[_i].id, this.myAdsBids);

      }  
      

    });

   
  }

  refreshAll() {

    this.getMyAds();
    this.getOfficialAds();
    this.getOtherUsersAds();
  }

  getOfficialAds() {

    this.adService.getAdsByConfirmed(3).subscribe(data=> {

      this.officialAds = data;
      //alert("off ads: " + JSON.stringify(this.officialAds));

      for (var _i = 0; _i < this.officialAds.length; _i++) {

        this.getBidsForSelectedAd(this.officialAds[_i].id, this.officialAdsBids);

      }

    });
  }

  getOtherUsersAds() {

    this.adService.getOtherUsersAds(this.currentUser.id, 1).subscribe(data=> {
      
      this.otherUsersAds = data;
      console.log("oglasi drugih usera:");
      console.log(this.otherUsersAds);  
      console.log("------");
    
      for (var _i = 0; _i < this.otherUsersAds.length; _i++) {

        this.getBidsForSelectedAd(this.otherUsersAds[_i].id, this.otherUsersAdsBids);

      }

    });

  }

  getBidsForSelectedAd(ad_id : number, bids : any) {

    this.bidService.getBidsForSelectedAd(ad_id).subscribe(data=> { 
      
      bids.set(ad_id, data); 
      console.log(bids);
      console.log("----");
    
    });

  }

  makeABid(offeredMoney : number, selectedAd : any) {

    this.bidService.registerBid({money : offeredMoney, ad : selectedAd, user : this.currentUser}).subscribe(data => {
      
      
      console.log("BID:");
      console.log(data);
      console.log("----");
      
    
    });

    this.refreshAll();

  }

  sendNotifications(bids : any, indexDobitnika : number, ad : any) {

    alert(ad.user.firstName);

    for (var i = 0; i < bids.length; i++) {
      
      if (i != indexDobitnika) {

        this.notificationService.registerNotification({message : "Your bid on ad " + ad.name + " [" + bids[i].money + " rsd] has been rejected by user " +  ad.user.firstName + " " + ad.user.lastName, user : bids[i].user}).subscribe(data => this.getMyAds());
        
      }
      
    }

    this.notificationService.registerNotification({message : "Your bid on ad " + ad.name + " [" + bids[indexDobitnika].money + " rsd] has been accepted by user " +  ad.user.firstName + " " + ad.user.lastName, user : bids[indexDobitnika].user}).subscribe(data => this.getMyAds());
 
    ad.confirmed = 4;
    this.adService.changeAdStatus(ad).subscribe(data =>  this.getOfficialAds());

  }

  

  getNotificationsForCurrentUser() {

    this.notificationService.getNotificationsForCurrentUser(this.currentUser.id).subscribe(data=> {
      
      this.myNotifications = data;

      for (var i = 0; i < this.myNotifications.length; i++) {
      
        this.showNotification(this.myNotifications[i].message);
        
      }

      for (var i = 0; i < this.myNotifications.length; i++) {
      
        this.notificationService.deleteNotificationById(this.currentUser.id).subscribe(data=> console.log("ocistio"));

      }
    });
  }
  
}


