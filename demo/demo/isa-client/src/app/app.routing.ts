import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';
import { RegisterCinemaComponent } from "./components/register-cinema/register-cinema.component";
import { LoginComponent } from "./components/login/login.component";
import { TestComponent } from "./components/test/test.component";
import { RegisterUserComponent } from "./components/register-user/register-user.component";
import { RegistrationConfirmComponent } from "./components/registration-confirm/registration-confirm.component";

import { AdminProfilePageComponent } from "./components/admin-profile-page/admin-profile-page.component";
import { FanZoneComponent } from "./components/fan-zone/fan-zone.component";

import { CinemaRepertoireComponent } from './components/cinema-repertoire/cinema-repertoire.component'
import { TheaterRepertoireComponent } from './components/theater-repertoire/theater-repertoire.component'



const appRoutes: Routes = 
[

    {
        path: 'home-guest',
        component: HomeGuestComponent
    },

    {
        path: 'cinema-repertoire',
        component: CinemaRepertoireComponent
    },

    {
        path: 'theater-repertoire',
        component: TheaterRepertoireComponent
    },

    {
        path: 'view-theaters',
        component: ViewTheatersComponent
    },

    {
        path: 'view-cinemas',
        component: ViewCinemasComponent
    },
    {
        path:'login',
        component: LoginComponent
    },
    {
        path:'register',
        component: RegisterUserComponent
    },
   {
        path:'registrationConfirm',
        component:RegistrationConfirmComponent
    },
    {
        path:'test',
        component: TestComponent
    }
    ,
    {
        path: '',
        redirectTo: '/login',
        pathMatch:'full'
        //component: LoginComponent
    },

    {

        path: 'registerCinema',
        component:  RegisterCinemaComponent
    },

    {

        path: 'adminProfile',
        component:  AdminProfilePageComponent
        
    },

    {
        path: 'fanZone',
        component: FanZoneComponent
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);