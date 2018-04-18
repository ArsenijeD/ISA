import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers} from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/Rx'


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';

@Injectable()
export class AdService {

  constructor(private http: Http) { }


  registerAd(ad : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(ad));
    return this.http.post('http://localhost:8080/public/oglasi/register', 
      JSON.stringify(ad), { headers : headers }).map((data : Response) => data.json());
  }

  getAdsOfCurrentUser(){

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/oglasi/getOglasForCurrentUser", {headers:headers}).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  getAdsByConfirmed(confirmed : number){

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/oglasi/getOglasByConfirmed/" + confirmed, {headers:headers}).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  changeAdStatus(ad : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(ad));
    return this.http.put('http://localhost:8080/public/oglasi/changeOglasStatus', 
      JSON.stringify(ad), { headers : headers }).map((data : Response) => data.json());

  }

}
