import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers} from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/Rx'
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';
@Injectable()
export class BidService {

  constructor(private http: Http) { }

  getBidsForSelectedAd(ad_id : number){

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/bids/getBidsForSelectedOglas/" + ad_id).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

}
