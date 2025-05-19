import { Routes } from "@angular/router";
import { UserDashboardComponent } from "./dashboard/user-dashboard.component";
import { UserBeneficiariesComponent } from "./beneficiaries/user-beneficiaries.component";
import { UserSettingsComponent } from "./settings/user-settings.component";
import { AccountsOverviewComponent } from "./accounts/accounts-overview/accounts-overview.component";
import { AccountDetailsComponent } from "./accounts/account-details/account-details.component";
import { UserTransactionsComponent } from "./transactions/user-transactions.component";
import { UserAuthGuard } from "../../authguard/user-auth-guard";
import { AddBeneficiaryComponent } from "./beneficiaries/add-beneficiary/add-beneficiary.component";
import { EditBeneficiaryComponent } from "./beneficiaries/edit-beneficiary/edit-beneficiary.component";

export const usersRoutes: Routes = [
    { path: "user/dashboard", component: UserDashboardComponent, canActivate: [UserAuthGuard] },

    { path: "user/beneficiaries", component: UserBeneficiariesComponent, canActivate: [UserAuthGuard] },
    { path: "user/beneficiaries/add", component: AddBeneficiaryComponent, canActivate: [UserAuthGuard] },
    { path: "user/beneficiaries/:id/edit", component: EditBeneficiaryComponent, canActivate: [UserAuthGuard] },

    { path: "user/settings", component: UserSettingsComponent, canActivate: [UserAuthGuard] },

    { path: "user/accounts", component: AccountsOverviewComponent, canActivate: [UserAuthGuard] },
    { path: "user/accounts/:id", component: AccountDetailsComponent, canActivate: [UserAuthGuard] },

    { path: "user/transactions", component: UserTransactionsComponent, canActivate: [UserAuthGuard] },
];
