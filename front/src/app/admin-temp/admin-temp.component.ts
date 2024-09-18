import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {SideBarComponent} from "./side-bar/side-bar.component";
import {HeaderComponent} from "./header/header.component";

@Component({
  selector: 'app-admin-temp',
  standalone: true,
  imports: [
    RouterOutlet,
    SideBarComponent,
    HeaderComponent
  ],
  templateUrl: './admin-temp.component.html',
  styleUrl: './admin-temp.component.css'
})
export class AdminTempComponent {

}
