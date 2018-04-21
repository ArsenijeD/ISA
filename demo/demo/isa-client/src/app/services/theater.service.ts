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

  getTheaterById(theaterID : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/public/theaters/getTheaterById/'+JSON.stringify(theaterID), 
      { headers : headers }).map((data : Response) => data.json());

  }


  registerTheater(theater : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(theater));
    return this.http.post('http://localhost:8080/public/theaters/register', 
      JSON.stringify(theater), { headers : headers }).map((data : Response) => data.json());
  }

  updateTheater(theater : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(theater));
    return this.http.put('http://localhost:8080/public/theaters/changeTheaterAdmin', 
      JSON.stringify(theater), { headers : headers }).map((data : Response) => data.json());
  }

  changeTheater(theater :any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(theater));
    return this.http.put('http://localhost:8080/public/theaters/changeTheater', 
      JSON.stringify(theater), { headers : headers }).map((data : Response) => data.json());

  }

  selectTheater(theater: any) {

    this.t.next(theater);
  }



  registerPerformance(performance : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(performance));
    return this.http.post('http://localhost:8080/public/performances/registerPerformance', 
      JSON.stringify(performance), { headers : headers }).map((data : Response) => data.json());

  }

  updatePerformance(performance : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(performance));
    return this.http.put('http://localhost:8080/public/performances/updatePerformance', 
      JSON.stringify(performance), { headers : headers }).map((data : Response) => data.json());
  }

  deletePerformance(performanceID : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(performanceID));
    return this.http.delete('http://localhost:8080/public/performances/deletePerformance/'+JSON.stringify(performanceID), 
       { headers : headers }).map((data : Response) => data.json());

  }

  addStage(stageDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(stageDTO));
    return this.http.post('http://localhost:8080/public/stages/addStage',
    JSON.stringify(stageDTO), { headers : headers }).map((data : Response) => data.json());

  }

  deleteStage(theaterID:any, stageID:any) {
    const headers = new Headers();
    console.log("cinema id:"+theaterID + "    " + "hallID: " + stageID);
    headers.append('Content-Type', 'application/json');
    return this.http.delete('http://localhost:8080/public/stages/deleteStage/'+JSON.stringify(theaterID)+'/'+JSON.stringify(stageID), 
    { headers : headers }).map((data: Response) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

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
    return this.http.put('http://localhost:8080/public/presentations/updatePresentation',
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


  rateTheater(ratingTheaterDTO) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/public/theaters/rateTheater', 
      JSON.stringify(ratingTheaterDTO), { headers : headers }).map((data : Response) => data.json());

  }



}
