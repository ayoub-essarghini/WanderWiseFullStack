import { Component } from '@angular/core';
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
  ],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  currencies = [
    { label: 'USD', value: 'USD' },
    { label: 'EUR', value: 'EUR' },
    { label: 'GBP', value: 'GBP' },
  ];
  selectedCurrency = 'USD'; // Default currency

  usefulLinks = [
    { label: 'Travel Blog & Tips', icon: 'pi pi-book' },
    { label: 'Working With Us', icon: 'pi pi-users' },
    { label: 'Be Our Partner', icon: 'pi pi-handshake' },
  ];


}
