import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

export const AdminAuthGuard = () => {
    const auth = inject(AuthService);
    const router = inject(Router);

    if(!auth.isAdmin()) {
        router.navigateByUrl('/login')
        return false
    }
    return true
}