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


const appRoutes: Routes = 
[

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
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);