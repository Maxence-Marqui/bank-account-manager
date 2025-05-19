import { Routes } from "@angular/router";
import { AdminDashboardComponent } from "./admin-dashboard/admin-dashboard.component";
import { AdminAuthGuard } from "../../authguard/admin-auth-guard";



export const adminsRoutes: Routes = [
    {path: "admin/dashboard", component: AdminDashboardComponent, canActivate: [AdminAuthGuard]},
];
