import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { capitalizeFirstLetter } from "../../shared/utils";

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  user: any = null;
  role: any = null;

  constructor(private authService: AuthService, private router: Router){

  }

  ngOnInit(){
    this.authService.user$.subscribe(user => {
      this.user = user;
      this.role = capitalizeFirstLetter(this.authService.getRole())
    })
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }

  getShownName(){
    if(this.role == "user") return this.user.firstName + " " + this.user.lastName
    else return this.user.email
  }

  getDashboardLink(){
    if(this.role == "User") return "/user/dashboard"
    if(this.role == "Admin") return "/admin/dashboard"
    return ""
  }

}
