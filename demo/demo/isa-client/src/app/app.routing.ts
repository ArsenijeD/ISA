import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./components/login-component.component";


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
    }

]




export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);