import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = "http://localhost:8080"
  private accountsUrl = this.apiUrl + "/accounts"
  private userUrl = this.apiUrl + "/users"

  constructor(private http: HttpClient){}


  createNewAccount(account: any){
    return this.http.post(this.accountsUrl, account)
  }

  loadAccount(id: number){
    return this.http.get(this.accountsUrl + "/" +id)
  }

  updateAccount(id: number, account: any){
    return this.http.patch(this.accountsUrl + "/" + id, account)
  }

  deleteAccount(id: number){
    return this.http.delete(this.accountsUrl + "/" + id)
  }


  loadUserAccounts(){
    return this.http.get(this.userUrl + "/accounts")
  }

  loadUsersOfAccount(accountId: number){
    return this.http.get(this.accountsUrl + "/" + accountId)
  }

  addUserToAccount(accountId: number, userId: number){
    return this.http.post(this.apiUrl + "/accounts/"+accountId + "/users/" + userId, {})
  }

  removeUserFromAccount(accountId: number, userId: number){
    return this.http.delete(this.apiUrl + "/accounts/" + accountId + "/users/" + userId)
  }
}
