import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AccountService } from '../../../../services/account.service';
import { UserService } from '../../../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-manage-account-users',
  templateUrl: './manage-account-users.component.html',
  styleUrls: ['./manage-account-users.component.css'],
  imports: [CommonModule, FormsModule, RouterModule],
})
export class ManageAccountUsersComponent implements OnInit {
  accountId!: number;
  account: any;
  accountUsers: any = [];
  allUsers: any = [];
  selectedUser: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.accountId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadAccount();
    this.loadAccountUsers();
    this.loadAllUsers();
  }

  loadAccount() {
    this.accountService.loadAccount(this.accountId).subscribe({
      next: (data) => (this.account = data),
      error: (err) => console.error('Erreur lors du chargement du compte', err)
    });
  }

  loadAccountUsers() {
    this.accountService.loadUsersOfAccount(this.accountId).subscribe({
      next: (data) => (this.accountUsers = data),
      error: (err) => console.error('Erreur lors du chargement des utilisateurs', err)
    });
  }

  loadAllUsers() {
    this.userService.getAllUsers().subscribe({
      next: (data) => (this.allUsers = data),
      error: (err) => console.error('Erreur lors du chargement des utilisateurs', err)
    });
  }

  addUser() {
    if (!this.selectedUser) return;
    this.accountService.addUserToAccount(this.accountId, this.selectedUser).subscribe({
      next: () => this.loadAccountUsers(),
      error: (err) => console.error('Erreur lors de l’ajout', err)
    });
  }

  removeUser(userId: number) {
    if (!confirm('Voulez-vous vraiment supprimer cet utilisateur du compte ?')) return;
    this.accountService.removeUserFromAccount(this.accountId, userId).subscribe({
      next: () => this.loadAccountUsers(),
      error: (err) => console.error('Erreur lors de la suppression', err)
    });
  }
}
