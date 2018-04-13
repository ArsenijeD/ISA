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

import { RegisterCinemaComponent } from './components/register-cinema/register-cinema.component';

import { CinemaService } from './services/cinema.service';
import { CinemaRepertoireComponent } from './components/cinema-repertoire/cinema-repertoire.component';
import { TheaterRepertoireComponent } from './components/theater-repertoire/theater-repertoire.component'
import { TheaterService } from './services/theater.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent,
    RegisterCinemaComponent,
    CinemaRepertoireComponent,
    TheaterRepertoireComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule
  ],
  providers: [HttpClientModule, CinemaService, TheaterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
