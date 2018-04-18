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
export class CinemaService {


  private c = new BehaviorSubject<any>(null);
  currentCinema = this.c.asObservable();


  constructor(private http: Http) { 

  }

  getCinemas(){


    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/cinemas/getAll", {headers:headers}).map(data => data.json())

/    .catch((err:HttpErrorResponse) =>
    {
        // alert(err.status + " " + err.error.error + " \n" + err.error.message);
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


  updateCinema(cinema : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(cinema));
    return this.http.put('http://localhost:8080/public/cinemas/changeCinemaAdmin', 
      JSON.stringify(cinema), { headers : headers }).map((data : Response) => data.json());

  }
  
  selectCinema(cinema : any) {

    this.c.next(cinema);
  }


  // deleteProjectionById(hallID : any, projectionID : any) {
  //   let params = new HttpParams().set('hallID', hallID); 
  //   params = params.set('projectionID', projectionID);   

  //   const headers = new Headers();
  //   headers.append('Content-Type', 'application/json');

  //   return this.http
  //   .delete("http://localhost:8080/public/projections/deleteProjection", {params:params,headers:headers})
  //   .map((data) => data.json())
  //   .catch((err:HttpErrorResponse) =>
  //   {
  //       alert(err.status + " " + err.error.error + " \n" + err.error.message);
  //       return Observable.throw(err);
  //   });

  // }

  deleteProjectionById(cinemaID : any, hallID : any, projectionID : any) {  
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.delete("http://localhost:8080/public/projections/deleteProjection/"+
    JSON.stringify(cinemaID)+"/"+JSON.stringify(hallID)+"/"+JSON.stringify(projectionID),
    {headers:headers})
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }

  registerProjection(projectionDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(projectionDTO));
    return this.http.post('http://localhost:8080/public/projections/registerProjection',
    JSON.stringify(projectionDTO), { headers : headers }).map((data : Response) => data.json());

  }

  updateProjection(projectionDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(projectionDTO));
    return this.http.post('http://localhost:8080/public/projections/updateProjection',
    JSON.stringify(projectionDTO), { headers : headers }).map((data : Response) => data.json());
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

  getProjectionsByHallID(hallID : any) {
    
    // console.log("ID sale je : " + hallID);

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/projections/getProjectionsByHallID/"+JSON.stringify(hallID), {headers:headers}).map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }

  getFilmByName(filmName : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/films/getFilmByName/"+JSON.stringify(filmName), {headers:headers}).map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }


  getFilms(){
    
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/public/films/getAll", {headers:headers}).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }


}
