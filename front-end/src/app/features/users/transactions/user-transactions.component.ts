import { Component } from '@angular/core';
import { AccountService } from '../../../services/account.service';
import { BeneficiariesService } from '../../../services/beneficiaries.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../../../services/transactions.service';

@Component({
  selector: 'app-user-transactions',
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './user-transactions.component.html',
  styleUrl: './user-transactions.component.css'
})
export class UserTransactionsComponent {
  userAccounts: any = [];
  userBeneficiaries: any = [];
  recentTransactions: any[] = [];
  transactionType: 'BENEFICIARY' | 'SELF' = 'BENEFICIARY';

  transaction = {
    fromAccountNumber: null,
    toAccountNumber: null,
    amount: null,
    date: new Date().toISOString().slice(0, 16),
    note: '',
    type: this.transactionType
  };

  constructor(
    private accountService: AccountService,
    private beneficiaryService: BeneficiariesService,
    private transactionService: TransactionService
  ) {}

  ngOnInit(): void {
    this.loadUserAccounts();
    this.loadUserBeneficiaries();
    this.loadRecentTransactions();
  }

  loadUserAccounts() {
    this.accountService.loadUserAccounts().subscribe(accounts => {
      this.userAccounts = accounts;
    });
  }

  loadUserBeneficiaries() {
    this.beneficiaryService.loadUserBeneficiaries().subscribe(beneficiaries => {
      this.userBeneficiaries = beneficiaries;
    });
  }

  loadRecentTransactions() {
    this.transactionService.getRecentTransactions().subscribe(transactions => {
      this.recentTransactions = transactions;
    });
  }

  setTransactionType(type: 'BENEFICIARY' | 'SELF') {
    this.transaction.type = type;
    this.transaction.toAccountNumber = null;
  }

  submitTransaction() {
    if (!this.transaction.fromAccountNumber || !this.transaction.toAccountNumber || !this.transaction.amount) {
      alert("Veuillez remplir tous les champs !");
      return;
    }

    this.transactionService.makeTransaction(this.transaction).subscribe(
      () => {
        alert("Transaction effectuée avec succès !");
        this.loadRecentTransactions(); // Recharger l'historique après la transaction
      },
      error => {
        alert("Erreur lors de la transaction : " + error.message);
      }
    );
  }
}
