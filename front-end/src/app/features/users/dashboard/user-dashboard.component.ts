import { Component } from '@angular/core';
import { UserActiveAccountsComponent } from "./active-accounts/user-active-accounts.component";
import { UserLastBeneficiariesComponent } from "./last-beneficiaries/user-last-beneficiaries.component";
import { UserLastTransactionsComponent } from "./last-transactions/user-last-transactions.component";
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-user-dashboard',
  imports: [UserActiveAccountsComponent, UserLastBeneficiariesComponent, UserLastTransactionsComponent],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent {

  lastAccountsUsed: any[] = [];
  lastTransactionsMade: any[] = [];
  lastBeneficiariesUsed: any[] = [];

  constructor(private userService: UserService){}

  ngOnInit(): void {

    
    this.userService.loadUserDashboard().subscribe({
      next: (data) => {
        this.lastAccountsUsed = data.accounts ?? [];
        this.lastTransactionsMade = data.transactions ?? [];
        this.lastBeneficiariesUsed = data.beneficiaries ?? [];
      },
      error: (err) => {
        console.error('Erreur lors du chargement du tableau de bord', err);
      }
    });
  }

}
