import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AdminTempComponent} from "./admin-temp/admin-temp.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminTempComponent,children :[
      { path: 'dashboard', component: DashboardComponent }
    ] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
