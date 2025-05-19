import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BeneficiariesService } from '../../../../services/beneficiaries.service';

@Component({
  selector: 'app-add-beneficiary',
  templateUrl: './add-beneficiary.component.html',
  styleUrls: ['./add-beneficiary.component.css'],
  imports: [ReactiveFormsModule, CommonModule, RouterModule]
})
export class AddBeneficiaryComponent {
  beneficiaryForm: FormGroup;
  
  constructor(
    private fb: FormBuilder,
    private beneficiaryService: BeneficiariesService,
    private router: Router
  ) {
    this.beneficiaryForm = this.fb.group({
      accountName: ['', [Validators.required, Validators.minLength(3)]],
      accountNumber: ['', [Validators.required]]
    });
  }

  get f() {
    return this.beneficiaryForm.controls;
  }

  onSubmit() {
    if (this.beneficiaryForm.invalid) {
      return;
    }

    this.beneficiaryService.addBeneficiary(this.beneficiaryForm.value).subscribe({
      next: () => {
        this.router.navigate(['/user/beneficiaries']);
      }
    });
  }
}
