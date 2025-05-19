import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-last-beneficiaries',
  imports: [CommonModule, RouterModule],
  templateUrl: './user-last-beneficiaries.component.html',
  styleUrl: './user-last-beneficiaries.component.css'
})
export class UserLastBeneficiariesComponent {

  @Input() beneficiaries: any = null;

  constructor(){}
}
