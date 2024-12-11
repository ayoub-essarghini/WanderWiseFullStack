import { Component } from '@angular/core';
import { NgStyle } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { MenuModule } from 'primeng/menu';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [
    CardModule,
    ButtonModule,
    DividerModule,
    DropdownModule,
    FormsModule,
    MenuModule,
    NgStyle
  ],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {



}
