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

import {ToastModule} from 'ng2-toastr/ng2-toastr';  
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';



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

import { FriendsService } from './services/friends.service'

import { BidService } from './services/bid.service';
import { NotificationService } from './services/notification.service';

//httpClient interceptor
import { TokenInterceptorService } from './services/token-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import {NgbModule} from '@ng-bootstrap/ng-bootstrap';





import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { CinemaDetailsComponent } from './components/cinema-details/cinema-details.component';
import { TheaterDetailsComponent } from './components/theater-details/theater-details.component';


//http interceptor
import {MyCustomHttp} from './services/my-custom-http';
import {RequestOptions, XHRBackend} from '@angular/http';
import { Router } from '@angular/router';
import { RegistrationConfirmComponent } from './components/registration-confirm/registration-confirm.component';
import { UserProfilePageComponent } from './components/user-profile-page/user-profile-page.component';
import { FriendsComponent } from './components/friends/friends.component';
import { ReservationComponent } from './components/reservation/reservation.component';
// factory dependency injection
export function providerCustomHttp(backend: XHRBackend, defaultOptions: RequestOptions, auth: AuthServiceService, router: Router) {
  return new MyCustomHttp(backend, defaultOptions, auth, router);
}
import { OrderModule } from 'ngx-order-pipe';
import { HomeUserComponent } from './components/home-user/home-user.component';
import { LoginLogoutComponent } from './components/login-logout/login-logout.component';
import { TicketPreviewComponent } from './components/ticket-preview/ticket-preview.component'; // <-- Import OrderModule

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

    UserProfilePageComponent,
    FriendsComponent,

    CinemaDetailsComponent,
    TheaterDetailsComponent,
    FanZoneAdminProfileComponent,
    UserProfilePageComponent,
    ReservationComponent,
    HomeUserComponent,
    LoginLogoutComponent,
    TicketPreviewComponent
  ],
  imports: [
    OrderModule,
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    FormsModule,
    BrowserAnimationsModule,
    ToastModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBWhwJkhsPej0DGz5a0MKu-x_24m6RBVYc',
      libraries: ["places"]
    })
  ],




  providers: [HttpClientModule, CinemaService, LoginService,AuthServiceService, 
    TheaterService, UserService, AdService, GeocoderService,BidService,FriendsService,NotificationService,

    {
      provide: Http,
      useFactory: providerCustomHttp,
      deps: [XHRBackend, RequestOptions, AuthServiceService, Router]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
