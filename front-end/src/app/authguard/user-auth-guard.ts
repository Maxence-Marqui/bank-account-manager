import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

export const UserAuthGuard = () => {
    const auth = inject(AuthService);
    const router = inject(Router);

    if(!auth.isUser()) {
        router.navigateByUrl('/login')
        return false
    }
    return true
}