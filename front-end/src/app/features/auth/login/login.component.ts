import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule, MatSlideToggleModule]
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  isAdmin: boolean = false;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  onLogin() {
    this.authService.login(this.email, this.password, this.isAdmin).subscribe({
      next: () => {
        if(this.isAdmin) this.router.navigate(["/admin/dashboard"])
        else this.router.navigate(["/user/dashboard"])
      },
      error: err => this.errorMessage = 'Email ou mot de passe incorrect'
    });
  }

  onToggle() {
    this.isAdmin = !this.isAdmin;
  }
}

