import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { FormsModule} from '@angular/forms'


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-component.component';
import { routing } from './app.routing';
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';

import { CinemaService } from './services/cinema.service'
import { UserService } from './services/user.service'
import { AdService } from './services/ad.service'

import { RegisterCinemaComponent } from './components/register-cinema/register-cinema.component';


import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AdminProfilePageComponent } from './components/admin-profile-page/admin-profile-page.component';
import { FanZoneComponent } from './components/fan-zone/fan-zone.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent,
    LoginComponent,
    RegisterCinemaComponent,
    AdminProfilePageComponent,
    FanZoneComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    FormsModule
  ],
  providers: [HttpClientModule, CinemaService, UserService, AdService],
  bootstrap: [AppComponent]
})
export class AppModule { }
