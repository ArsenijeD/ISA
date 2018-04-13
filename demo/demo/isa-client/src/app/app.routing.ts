import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./components/login-component.component";
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';
import { RegisterCinemaComponent } from "./components/register-cinema/register-cinema.component";
import { CinemaRepertoireComponent } from './components/cinema-repertoire/cinema-repertoire.component'
import { TheaterRepertoireComponent } from './components/theater-repertoire/theater-repertoire.component'


const appRoutes: Routes = 
[
    {
        path: 'login',
        component: LoginComponent
    },

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
        path: '',
        redirectTo: '/login',
        pathMatch:'full'
        //component: LoginComponent
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);