import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./components/login-component.component";
import { RegisterCinemaComponent } from "./components/register-cinema/register-cinema.component";


const appRoutes: Routes = 
[
    {
        path: 'login',
        component: LoginComponent
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
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);