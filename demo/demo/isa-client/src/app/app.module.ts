import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
import { HttpClientModule, HttpClient,} from '@angular/common/http';
import { HttpModule,Http } from '@angular/http';

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

//services
import { CinemaService } from './services/cinema.service';
import { LoginService } from './services/login.service';
import { AuthServiceService} from './services/auth-service.service';
//httpClient interceptor
import { TokenInterceptorService } from './services/token-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

//http interceptor
import {MyCustomHttp} from './services/my-custom-http';
import {RequestOptions, XHRBackend} from '@angular/http';
import { Router } from '@angular/router';
import { RegistrationConfirmComponent } from './components/registration-confirm/registration-confirm.component';
// factory dependency injection
export function providerCustomHttp(backend: XHRBackend, defaultOptions: RequestOptions, auth: AuthServiceService,router:Router) {
  return new MyCustomHttp(backend, defaultOptions, auth,router);
}
@NgModule({
  declarations: [
    AppComponent,
    HomeGuestComponent,
    ViewCinemasComponent,
    ViewTheatersComponent,
    RegisterCinemaComponent,
    LoginComponent,
    TestComponent,
    RegisterUserComponent,
    RegistrationConfirmComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule
  ],
  providers: [HttpClientModule, CinemaService, LoginService,AuthServiceService,
    //{ provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true } //httpClient Interceptor
    {
      provide: Http,
      useFactory: providerCustomHttp,
      deps: [XHRBackend, RequestOptions, AuthServiceService,Router]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
