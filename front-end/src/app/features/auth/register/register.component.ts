import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [ReactiveFormsModule, CommonModule]
})
export class RegisterComponent {
  registerForm: FormGroup;
  isAdmin: boolean = false;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      birthday: [null, Validators.required],
      phoneNumber: ['', [Validators.required]],
      adminTier:[0],
      accountStatus: [0]
    });
  }

  toggleAdmin() {
    this.isAdmin = !this.isAdmin;

    if (this.isAdmin) {
      this.registerForm.addControl('adminTier', this.fb.control('DEFAULT_TIER', Validators.required));
    } else {
      this.registerForm.removeControl('adminTier');
    }
  }


  onSubmit() {
    if (!this.isAdmin) {
      if (this.registerForm.valid) {
        this.authService.registerUser(this.registerForm.value).subscribe({
          next: () => this.router.navigate(["/login"])
        })
      } else {
        console.log('Le formulaire est invalide');
      }
    }
    else {
      if (this.registerForm.valid) {
        this.authService.registerAdmin(this.registerForm.value).subscribe({
          next: () => this.router.navigate(["/login"])
        })
      } else {
        console.log('Le formulaire est invalide');
      }
    }
  }
}

