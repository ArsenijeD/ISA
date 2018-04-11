import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-component.component';
import { routing } from './app.routing';
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';

import { CinemaService } from './services/cinema.service'
import { RegisterCinemaComponent } from './components/register-cinema/register-cinema.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent
    LoginComponent,
    RegisterCinemaComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule
  ],
  providers: [HttpClientModule, CinemaService, RegisterCinemaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
