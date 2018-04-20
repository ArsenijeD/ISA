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
export class FriendsService {

  constructor(private http: Http) { }

  searchUsers(firstName:String,lastName:String){
    return this.http.get("http://localhost:8080/friend/search/"+firstName+"/"+lastName).map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }
  getAllFriends(){
    return this.http.get("http://localhost:8080/friend/getAll").map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }
  getAllFriendReqSent(){                               
    return this.http.get("http://localhost:8080/friend/getAllPendingReqSent").map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  
  }
  getAllFriendReqReceived(){
    return this.http.get("http://localhost:8080/friend/getAllPendingReqReceived").map(data => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }

  unfriend(id:number) {  
    return this.http.get("http://localhost:8080/friend/unfriend/"+id)
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }

  sendRequest(id:number) {  
    return this.http.get("http://localhost:8080/friend/sendRequest/"+id)
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }

  rejectRequest(id:number) {  
    return this.http.get("http://localhost:8080/friend/rejectRequest/"+id)
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }
  approveRequest(id:number) {  
    return this.http.get("http://localhost:8080/friend/approveRequest/"+id)
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }

  deleteSentRequest(id:number) {  
    return this.http.get("http://localhost:8080/friend/deleteSentRequest/"+id)
    .map((data) => data.json())
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });
  }
}
