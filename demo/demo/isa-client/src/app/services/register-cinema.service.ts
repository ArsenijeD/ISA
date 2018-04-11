import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http'
import 'rxjs/Rx'

@Injectable()
export class RegisterCinemaService {

  constructor(private http : Http) { }

  registerCinema(cinema : any) {

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    alert(JSON.stringify(cinema));
    return this.http.post('http://localhost:8080/public/cinemas/register', 
      JSON.stringify(cinema), { headers : headers }).map((data : Response) => data.json());
  }
}
