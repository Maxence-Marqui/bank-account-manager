import { Routes } from '@angular/router';
import { LandingPageComponent } from './features/landing-page/landing-page.component';
import { authRoutes } from './features/auth/auth.routes';
import { usersRoutes } from './features/users/users.routes';
import { adminsRoutes } from './features/admins/admins.routes';

export const routes: Routes = [
    {path: "", component: LandingPageComponent},
    {path: '*', redirectTo: ''},
    ...authRoutes,
    ...usersRoutes,
    ...adminsRoutes,
    
];
