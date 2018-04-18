import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';

@Injectable()
export class TheaterService {

  private t = new BehaviorSubject<any>(null);
  currentTheater = this.t.asObservable();

  constructor(private http: Http) { 

  }

  getTheaters(){
    return this.http.get("http://localhost:8080/public/theaters/getAll").map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  selectTheater(theater: any) {

    this.t.next(theater);
  }


  deletePresentationById(theaterID : any, stageID : any, presentationID : any) {  
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.delete("http://localhost:8080/public/presentations/deletePresentation/"+
    JSON.stringify(theaterID)+"/"+JSON.stringify(stageID)+"/"+JSON.stringify(presentationID),
    {headers:headers})
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }

  registerPresentation(presentationDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(presentationDTO));
    return this.http.post('http://localhost:8080/public/presentations/registerPresentation',
    JSON.stringify(presentationDTO), { headers : headers }).map((data : Response) => data.json());

  }

  updatePresentation(presentationDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(presentationDTO));
    return this.http.post('http://localhost:8080/public/presentations/updatePresentation',
    JSON.stringify(presentationDTO), { headers : headers }).map((data : Response) => data.json());

  }


  getPerformances(){
    
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/performances/getAll", {headers:headers}).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }



}
