import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-active-accounts',
  imports: [CommonModule, RouterModule],
  templateUrl: './user-active-accounts.component.html',
  styleUrl: './user-active-accounts.component.css'
})
export class UserActiveAccountsComponent {

  @Input() accounts: any = null

  getAccountLink(accountId: BigInteger){
    return "/user/accounts/" + accountId
  }
}
