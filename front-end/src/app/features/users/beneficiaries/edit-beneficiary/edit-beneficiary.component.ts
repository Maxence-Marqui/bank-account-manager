import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserService } from '../../../../services/user.service';
import { BeneficiariesService } from '../../../../services/beneficiaries.service';

@Component({
  selector: 'app-edit-beneficiary',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './edit-beneficiary.component.html',
  styleUrl: './edit-beneficiary.component.css'
})
export class EditBeneficiaryComponent {
  beneficiaryForm: FormGroup;
  beneficiaryId: string | null = null;

    constructor(
      private fb: FormBuilder,
      private beneficiariesService: BeneficiariesService,
      private router: Router,
      private route: ActivatedRoute,
    ) {
      this.beneficiaryForm = this.fb.group({
        accountName: ['', [Validators.required, Validators.minLength(3)]],
        accountNumber: ['', [Validators.required]]
      });
    }

  ngOnInit(): void {
    this.beneficiaryId = this.route.snapshot.paramMap.get('id');
    this.beneficiariesService.loadUserBeneficiary(this.beneficiaryId).subscribe({
      next: (data) => this.beneficiaryForm.patchValue(data),
      error: (response) => console.error(response) 
    })
  }


  onSubmit() {
    if (this.beneficiaryForm.invalid) {
      return;
    }

    this.beneficiariesService.editBeneficiary(this.beneficiaryId, this.beneficiaryForm.value).subscribe({
      next: () => {
        this.router.navigate(['/user/beneficiaries']);
      }
    });
  }
}
