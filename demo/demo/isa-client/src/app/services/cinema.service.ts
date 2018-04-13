import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';

@Injectable()
export class CinemaService {


  private c = new BehaviorSubject<any>(null);
  currentCinema = this.c.asObservable();


  constructor(private http: Http) { 

  }

  getCinemas(){

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/cinemas/getAll", {headers:headers}).map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  registerCinema(cinema : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(cinema));
    return this.http.post('http://localhost:8080/public/cinemas/register', 
      JSON.stringify(cinema), { headers : headers }).map((data : Response) => data.json());
  }


  selectCinema(cinema : any) {

    this.c.next(cinema);
  }


  getHallsByCinemaID(cinemaID : any) {

    console.log(cinemaID);

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/halls/getHallsByCinemaID/"+JSON.stringify(cinemaID), {headers:headers}).map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }

}
