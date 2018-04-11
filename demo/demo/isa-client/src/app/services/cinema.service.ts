import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http} from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';

@Injectable()
export class CinemaService {

  constructor(private http: Http) { 

  }

  getCinemas(){
    return this.http.get("http://localhost:8080/public/cinemas/getAll").map(data => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

}
