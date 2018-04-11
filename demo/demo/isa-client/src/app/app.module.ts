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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule
  ],
  providers: [HttpClientModule, CinemaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
