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

    .catch((err:HttpErrorResponse) =>
    {
        // alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  getCinemaById(cinemaID : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/public/cinemas/getCinemaById/'+JSON.stringify(cinemaID), 
      { headers : headers }).map((data : Response) => data.json());

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


  changeCinema(cinema :any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(cinema));
    return this.http.put('http://localhost:8080/public/cinemas/changeCinema', 
      JSON.stringify(cinema), { headers : headers }).map((data : Response) => data.json());

  }

  
  selectCinema(cinema : any) {

    this.c.next(cinema);
  }

  registerFilm(film : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(film));
    return this.http.post('http://localhost:8080/public/films/registerFilm', 
      JSON.stringify(film), { headers : headers }).map((data : Response) => data.json());
  }

  updateFilm(film : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(film));
    return this.http.put('http://localhost:8080/public/films/updateFilm',        // ovde menjao
      JSON.stringify(film), { headers : headers }).map((data : Response) => data.json());
  }


  // deleteFilm(film : any) {

  //   const headers = new Headers();
  //   headers.append('Content-Type', 'application/json');
  //   alert(JSON.stringify(film));
  //   return this.http.post('http://localhost:8080/public/films/deleteFilm', 
  //     JSON.stringify(film), { headers : headers }).map((data : Response) => data.json());
  // }

  deleteFilm(filmID : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(filmID));
    return this.http.delete('http://localhost:8080/public/films/deleteFilm/'+JSON.stringify(filmID), 
       { headers : headers }).map((data : Response) => data.json());
  }


  addHall(hallDTO : any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(hallDTO));
    return this.http.post('http://localhost:8080/public/halls/addHall',
    JSON.stringify(hallDTO), { headers : headers }).map((data : Response) => data.json());

  }

  deleteHall(cinemaID:any, hallID:any) {
    const headers = new Headers();
    console.log("cinema id:"+cinemaID + "    " + "hallID: " + hallID);
    headers.append('Content-Type', 'application/json');
    return this.http.delete('http://localhost:8080/public/halls/deleteHall/'+JSON.stringify(cinemaID)+'/'+JSON.stringify(hallID), 
    { headers : headers }).map((data: Response) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

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
    return this.http.put('http://localhost:8080/public/projections/updateProjection',        // ovde menjao
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


  addFastTickets(FastTicketsDTO :any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(FastTicketsDTO));
    return this.http.put('http://localhost:8080/public/projections/addFastTickets', 
      JSON.stringify(FastTicketsDTO), { headers : headers }).map((data : Response) => data.json());

  }

  fastReserveTicket(ReservationTicketDTO :any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(ReservationTicketDTO));
    return this.http.put('http://localhost:8080/public/tickets/fastReserveTicket', 
      JSON.stringify(ReservationTicketDTO), { headers : headers }).map((data : Response) => data.json());

  }


  reserveTicket(ReservationTicketDTO :any) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(ReservationTicketDTO));
    return this.http.put('http://localhost:8080/public/tickets/reserveTicket', 
      JSON.stringify(ReservationTicketDTO), { headers : headers }).map((data : Response) => data.json());

  }

}
