import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BeneficiariesService {

  private apiUrl = "http://localhost:8080/users"

  constructor(private http: HttpClient) { }


  loadUserBeneficiary(id: any){
    return this.http.get(this.apiUrl + "/beneficiaries/" + id)
  }

  loadUserBeneficiaries(){
    return this.http.get(this.apiUrl + "/beneficiaries")
  }

  addBeneficiary(beneficiary: any){
    return this.http.post(this.apiUrl + "/beneficiaries", beneficiary)
  }

  editBeneficiary(id: any, beneficiary: any){
    return this.http.patch(this.apiUrl + "/beneficiaries/" + id, beneficiary)
  }

  removeBeneficiary(id: BigInt){
    return this.http.delete(this.apiUrl + "/beneficiaries/" + id)
  }
}
