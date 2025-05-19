import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit{

  private apiUrl = "http://localhost:8080/users"

  constructor(private http: HttpClient){}

  ngOnInit(): void {
      this.loadUserDashboard()
  }

  getAllUsers(){
    return this.http.get(this.apiUrl)
  }

  loadUserDashboard(){
    return this.http.get <{ accounts: any[], transactions: any[], beneficiaries: any[] }>(this.apiUrl + "/dashboard")
  }

  loadUserSettings(){
    return this.http.get(this.apiUrl + "/settings")
  }

  updateUserSettings(settings: any){
    return this.http.patch(this.apiUrl + "/settings", settings)
  }

  loadUserTransactions(){
    return this.http.get(this.apiUrl + "/transactions")
  }

}
