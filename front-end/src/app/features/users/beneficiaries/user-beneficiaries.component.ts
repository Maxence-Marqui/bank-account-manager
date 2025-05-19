import { Component } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BeneficiariesService } from '../../../services/beneficiaries.service';

@Component({
  selector: 'app-user-beneficiaries',
  imports: [CommonModule, RouterModule],
  templateUrl: './user-beneficiaries.component.html',
  styleUrl: './user-beneficiaries.component.css'
})
export class UserBeneficiariesComponent {
  beneficiaries: any = [];

  constructor(private beneficiaryService: BeneficiariesService) {}

  ngOnInit(): void {
    this.loadBeneficiaries();
  }

  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleDateString();
  }

  deleteBeneficiary(id: BigInt){
    this.beneficiaryService.removeBeneficiary(id).subscribe({
      next: (data) => this.beneficiaries = data,
      error: (err) => console.error('Erreur lors du chargement des bénéficiaires', err)
    })
  }

  loadBeneficiaries() {
    this.beneficiaryService.loadUserBeneficiaries().subscribe({
      next: (data) => this.beneficiaries = data,
      error: (err) => console.error('Erreur lors du chargement des bénéficiaires', err)
    });
  }
}
