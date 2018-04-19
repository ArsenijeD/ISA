import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
import { HttpClientModule, HttpClient,} from '@angular/common/http';
import { HttpModule,Http } from '@angular/http';
import { FormsModule} from '@angular/forms'



//componenets
import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';






import {LoginComponent} from './components/login/login.component';

import { RegisterCinemaComponent } from './components/register-cinema/register-cinema.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { TestComponent } from './components/test/test.component'
import { CinemaRepertoireComponent } from './components/cinema-repertoire/cinema-repertoire.component';
import { TheaterRepertoireComponent } from './components/theater-repertoire/theater-repertoire.component';
import { AdminProfilePageComponent } from './components/admin-profile-page/admin-profile-page.component';
import { FanZoneComponent } from './components/fan-zone/fan-zone.component';


import { AgmCoreModule} from '@agm/core';

import { FanZoneAdminProfileComponent } from './components/fan-zone-admin-profile/fan-zone-admin-profile.component'


//services

import { LoginService } from './services/login.service';
import { AuthServiceService} from './services/auth-service.service';
import { TheaterService } from './services/theater.service';
import { CinemaService } from './services/cinema.service';
import { UserService } from './services/user.service';
import { AdService } from './services/ad.service';
import { GeocoderService } from './services/geocoder.service';
import { BidService } from './services/bid.service';

//httpClient interceptor
import { TokenInterceptorService } from './services/token-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import {NgbModule} from '@ng-bootstrap/ng-bootstrap';





import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


//http interceptor
import {MyCustomHttp} from './services/my-custom-http';
import {RequestOptions, XHRBackend} from '@angular/http';
import { Router } from '@angular/router';
import { RegistrationConfirmComponent } from './components/registration-confirm/registration-confirm.component';
import { UserProfilePageComponent } from './components/user-profile-page/user-profile-page.component';
// factory dependency injection
export function providerCustomHttp(backend: XHRBackend, defaultOptions: RequestOptions, auth: AuthServiceService, router: Router) {
  return new MyCustomHttp(backend, defaultOptions, auth, router);
}
@NgModule({
  declarations: [
    AppComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent,
    RegisterCinemaComponent,
    TestComponent,
    LoginComponent,
    RegisterUserComponent,
    RegistrationConfirmComponent,
    RegisterCinemaComponent,
    AdminProfilePageComponent,
    FanZoneComponent,
    RegisterCinemaComponent,
    CinemaRepertoireComponent,
    TheaterRepertoireComponent,
    FanZoneAdminProfileComponent,
    UserProfilePageComponent

  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBWhwJkhsPej0DGz5a0MKu-x_24m6RBVYc',
      libraries: ["places"]
    })
  ],


  providers: [HttpClientModule, CinemaService, LoginService,AuthServiceService, TheaterService, UserService, AdService, GeocoderService,BidService,
    //{ provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true } //httpClient Interceptor
    {
      provide: Http,
      useFactory: providerCustomHttp,
      deps: [XHRBackend, RequestOptions, AuthServiceService, Router]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
