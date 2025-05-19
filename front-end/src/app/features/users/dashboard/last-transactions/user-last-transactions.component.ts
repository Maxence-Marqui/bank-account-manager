import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-last-transactions',
  imports: [CommonModule, RouterModule],
  templateUrl: './user-last-transactions.component.html',
  styleUrl: './user-last-transactions.component.css'
})
export class UserLastTransactionsComponent {

  @Input() transactions: any = null
}
