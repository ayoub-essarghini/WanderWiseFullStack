import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AdminTempComponent} from "./admin-temp/admin-temp.component";
import {HomeComponent} from "./home/home.component";
import {DestinationsComponent} from "./destinations/destinations.component";
import {AboutComponent} from "./about/about.component";
import {TripsComponent} from "./trips/trips.component";

export const routes: Routes = [

  { path: 'home', component: HomeComponent },
      { path: 'trips', component: TripsComponent },
      { path: 'destinations', component: DestinationsComponent },
      { path: 'about-us', component:  AboutComponent},
  // { path: '404', component: NotFoundComponent },
  { path: 'admin', component: AdminTempComponent,children :[
      { path: 'dashboard', component: DashboardComponent }
    ] },
  {
    path:'login', component: LoginComponent
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  // { path: '**', redirectTo: '/404' },
];
