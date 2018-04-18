import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';


import {Http, Response, Headers } from "@angular/http";

import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/Rx'


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';

@Injectable()
export class GeocoderService {

  constructor(private http: Http) { }


  getlatlng(address) {
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + address  + '&key=AIzaSyBWhwJkhsPej0DGz5a0MKu-x_24m6RBVYc').map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }
}
  

