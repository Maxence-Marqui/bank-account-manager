import { Component } from '@angular/core';
import { AccountService } from '../../../../services/account.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-accounts-overview',
  imports: [CommonModule, RouterModule, ReactiveFormsModule, FormsModule],
  templateUrl: './accounts-overview.component.html',
  styleUrl: './accounts-overview.component.css'
})
export class AccountsOverviewComponent {
  accounts: any = [];
  editingAccount: any = null;
  accountData = { accountName: '' };

  constructor(private accountService: AccountService, private router: Router) {}

  ngOnInit() {
    this.loadAccounts();
  }

  saveAccount() {
    if (this.editingAccount) {
      this.accountService.updateAccount(this.editingAccount.id, this.accountData).subscribe({
        next: (data) => {
          this.accounts = data
          this.cancelEdit();
        },
        error: (err) => console.error('Erreur lors de la modification', err)
      });
    } else {
      this.accountService.createNewAccount(this.accountData).subscribe({
        next: (data) => {
          this.accounts = data
          this.accountData.accountName = '';
        },
        error: (err) => console.error('Erreur lors de l’ajout', err)
      });
    }
  }

  editAccount(account: any) {
    this.editingAccount = account;
    this.accountData = { accountName: account.accountName };
  }

  cancelEdit() {
    this.editingAccount = null;
    this.accountData.accountName = '';
  }

  loadAccounts() {
    this.accountService.loadUserAccounts().subscribe({
      next: (data) => this.accounts = data,
      error: (err) => {
        console.error('Erreur lors du chargement des comptes', err);
      }
    });
  }

  deleteAccount(id: number) {
    if (confirm('Voulez-vous vraiment supprimer ce compte ?')) {
      this.accountService.deleteAccount(id).subscribe({
        next: (data) => this.accounts = data,
        error: (err) => {
          console.error('Erreur lors de la suppression', err);
        }
      });
    }
  }

  goToAccountDetails(accountId: number) {
    this.router.navigate(['/user/accounts/'+ accountId]);
  }
}
