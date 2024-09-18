import { Component } from '@angular/core';
import {HeaderComponent} from "../admin-temp/header/header.component";
import {SideBarComponent} from "../admin-temp/side-bar/side-bar.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    HeaderComponent,
    SideBarComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
