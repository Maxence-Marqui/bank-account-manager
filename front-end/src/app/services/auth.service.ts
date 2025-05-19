import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private userSubject = new BehaviorSubject<any>(null);
  public user$ = this.userSubject.asObservable();

  private userLoginUrl = "http://localhost:8080/auth/login/users";
  private adminLoginUrl = "http://localhost:8080/auth/login/admins";


  constructor(private http: HttpClient) {
    this.loadUserData();
  }

  private loadUserData(): void {
    const storedUser = localStorage.getItem("entityData");
    if (storedUser) {
      this.userSubject.next(JSON.parse(storedUser));
    }
  }

  registerAdmin(admin: any){
    return this.http.post("http://localhost:8080/auth/register/admin", admin)
  }

  registerUser(user: any){
    return this.http.post("http://localhost:8080/auth/register/user", user)
  }

  login(email: string, password: string, isAdmin: boolean): Observable<any> {
    if (isAdmin) {
      return this.http.post<{ token: string, entity: JSON }>(`${this.adminLoginUrl}`, { email, password }).pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('entityData', JSON.stringify(response.entity))
          localStorage.setItem('role', 'admin');

          this.loadUserData();
        })
      );
    }
    else {
      return this.http.post<{ token: string, entity: JSON }>(`${this.userLoginUrl}`, { email, password }).pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('entityData', JSON.stringify(response.entity))
          localStorage.setItem('role', 'user');

          this.loadUserData()
        })
      );
    }

  }


  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem("entityData");
    localStorage.removeItem('role');
    this.userSubject.next(null);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUser(): any {
    return this.userSubject.value;
  }

  getRole(): string | null {
    return localStorage.getItem("role");
  }

  isAdmin(): boolean {
    return localStorage.getItem("role") == "admin"
  }

  isUser(): boolean {
    return localStorage.getItem("role") == "user"
  }
}
