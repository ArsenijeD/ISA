import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./components/login-component.component";
import { HomeGuestComponent } from './components/home-guest/home-guest.component';
import { ViewCinemasComponent } from './components/view-cinemas/view-cinemas.component';
import { ViewTheatersComponent } from './components/view-theaters/view-theaters.component';
import { RegisterCinemaComponent } from "./components/register-cinema/register-cinema.component";
import { AdminProfileComponent } from "./components/admin-profile/admin-profile.component";


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
    },

    {

        path: 'registerCinema',
        component:  RegisterCinemaComponent
    },

    {

        path: 'adminProfile',
        component:  AdminProfileComponent
        
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);