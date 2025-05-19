import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) {}

  makeTransaction(transaction: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, transaction);
  }

  getRecentTransactions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  getAccountTransactions(accountId: Number): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/${accountId}`);
  }
}
