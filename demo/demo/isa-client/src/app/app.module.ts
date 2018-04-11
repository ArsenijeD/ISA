import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-component.component';
import { routing } from './app.routing';
import { RegisterCinemaComponent } from './components/register-cinema/register-cinema.component';

import { RegisterCinemaService } from './services/register-cinema.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterCinemaComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    HttpModule
  ],
  providers: [RegisterCinemaService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
