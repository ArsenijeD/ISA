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
export class NotificationService {

  constructor(private http: Http) { }

  registerNotification(notification : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(notification));
    return this.http.post('http://localhost:8080/notifications/register', 
      JSON.stringify(notification), { headers : headers }).map((data : Response) => data.json());
  }

  getNotificationsForCurrentUser(id : number){

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/notifications/getNotificationsForCurrentUser/" + id , {headers:headers}).map(data => data.json())

    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }

  deleteNotificationById(userID : any) {  
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.delete("http://localhost:8080/notifications/deleteNotification/"+
    JSON.stringify(userID),
    {headers:headers})
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });

  }

}
