import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Chart } from 'chart.js/auto';
import { AccountService } from '../../../../services/account.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { capitalizeFirstLetter } from '../../../../shared/utils';
import { UserService } from '../../../../services/user.service';
import { Account, User } from '../../../../shared/interfaces';
import { TransactionService } from '../../../../services/transactions.service';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css'],
  imports: [CommonModule, RouterModule, FormsModule]
})
export class AccountDetailsComponent implements OnInit {
  account!: any;
  transactions: any = [];
  balanceChart: any = null;

  showAddUserForm = false;

  users: User[] = [];
  availableUsers: User[] = [];
  selectedUserId: number | null = null;

  constructor(private route: ActivatedRoute, private accountService: AccountService, private userService: UserService, private transactionService: TransactionService) {}

  ngOnInit() {
    const accountId = Number(this.route.snapshot.paramMap.get('id'));

    this.accountService.loadAccount(accountId).subscribe(account => {
      this.account = account;
      //@ts-ignore 
      this.users = account.users;
    });

    this.userService.getAllUsers().subscribe(users => {
      //@ts-ignore 
      this.availableUsers = users.filter(user => !this.account.users.some(accountUser => accountUser.id === user.id));
    });

    this.transactionService.getAccountTransactions(accountId).subscribe(transactions =>{
      this.transactions = transactions
      console.log(transactions)
    })
  }

  toggleAddUser() {
    this.showAddUserForm = !this.showAddUserForm;
  }

  formatName(firstName: string, lastName: string){
    return capitalizeFirstLetter(firstName) + " " + capitalizeFirstLetter(lastName)
  }

  loadAccountDetails(accountId: number) {
    this.accountService.loadAccount(accountId).subscribe({
      next: (data: any) => {
        this.account = data;
        this.transactions = data.transactions;
        this.createBalanceChart();
      },
      error: (err) => console.error('Erreur lors du chargement du compte', err)
    });
  }

  addUser() {
    if (this.selectedUserId) {
      this.accountService.addUserToAccount(this.account.id, this.selectedUserId).subscribe(() => {
        alert('Utilisateur ajouté avec succès');
        this.ngOnInit();
      });
    }
  }

  removeUser(userId: number) {
    if (confirm('Voulez-vous vraiment supprimer cet utilisateur du compte ?')) {
      this.accountService.removeUserFromAccount(this.account.id, userId).subscribe(() => {
        this.ngOnInit();
      });
    }
  }

  createBalanceChart() {
    if (this.balanceChart) {
      this.balanceChart.destroy();
    }

    const ctx = document.getElementById('balanceChart') as HTMLCanvasElement;
    this.balanceChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.transactions.map((transaction: any) => new Date(transaction.date).toLocaleDateString()),
        datasets: [{
          label: 'Balance (€)',
          data: this.transactions.map((transaction: any) => transaction.balanceAfterTransaction),
          borderColor: 'blue',
          fill: false
        }]
      },
      options: {
        responsive: true
      }
    });
  }
}
