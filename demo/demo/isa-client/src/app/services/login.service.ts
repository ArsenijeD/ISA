import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpClient, HttpErrorResponse, HttpParams,HttpHeaders } from '@angular/common/http';
//import { RequestOptions } from '@angular/http';
//import { Router } from '@angular/router';

import { AuthServiceService } from './auth-service.service'
import { MyCustomHttp } from './my-custom-http';
@Injectable()
export class LoginService {
  auth:any;
  constructor(private http: Http,auth: AuthServiceService) {
  this.auth=auth;
   }

  loginUser(user : any) {
    var token=btoa( user.name+':'+user.password);
    this.auth.setToken(token);
    let headers = new HttpHeaders();
    headers.append('Authorization' ,`Basic ${this.auth.getToken()}`);

  return this.http
  .get("http://localhost:8080/angularUser")
  .map((data:Response) => console.log(data));
  
  }

  registerUser(user : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(user));
    return this.http.post('http://localhost:8080/public/user/create', 
      JSON.stringify(user), { headers : headers }).map((data : Response) => data.json());
  }
  
  confirmRegistration(token:string){
    return this.http.get('http://localhost:8080/public/registrationConfirm?token='+token);
  }
}

/*
loginUser(user : any) {

  var headers = new Headers();
  headers.append('Content-Type', 'application/x-www-form-urlencoded');
  headers.append('Data-Type','json');
 // var params = new HttpParams();
 // params.set('email', user.email);
 // params.set('password', user.password);



  alert(JSON.stringify(user));
  var body="email="+user.name+"&password="+user.password+"&remember-me="+user.rememberMe;
  
 // return this.http.post("http://localhost:8080/login", body, { headers : headers }).map((data : Response) => data.json());
 return this.http
 .post("http://localhost:8080/login", body, { headers : headers })
 .map((data:Response) => console.log(data));
 
}
}
*/
/*loginUser(user : any) {

  let headers = new Headers({ 
    'Authorization': 'Basic ' + btoa(user.name + ':' + user.password),
    'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
});
  headers.append('Content-Type', 'application/x-www-form-urlencoded');
  headers.append('Data-Type','json');
 // var params = new HttpParams();
 // params.set('email', user.email);
 // params.set('password', user.password);

 
let options = new RequestOptions({ 
  headers: headers 
  });
alert(JSON.stringify(user));
  //var body="email="+user.name+"&password="+user.password+"&remember-me="+user.rememberMe;
  return this.http.post("http://localhost:8080/login", {}, options)
  .map((data:Response) => data)
  .catch(e => this.handleError(e)); // handle 401 error - bad credentials
              
           
  //return this.http.post("http://localhost:8080/login", body, { headers : headers }).map((data : Response) => data.json());
}
private handleError(error: any): Promise<any> {
  console.error('An error occurred', error); // for demo purposes only
  return Promise.reject(error.message || error);
}
}*/
// .subscribe((data) => {
//   if (data.json()) {
//       resolve(data.json());
//   } else {
//       console.log(data);
//       console.log("Error");
//   }
// })
// });