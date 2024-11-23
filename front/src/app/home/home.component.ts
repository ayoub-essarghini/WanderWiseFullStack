import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
  CommonModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  cards = [
    { id: 1, title: 'South Africa',img:'/assets/images/cat-soft-africa.jpg',tours:5, isHovered: false },
    { id: 2, title: 'Asia',img:'/assets/images/cat-asia.jpg',tours:3, isHovered: false },
    { id: 3, title: 'Western Europe',img:'/assets/images/cat-eu-1.jpg',tours:2, isHovered: false },
    { id: 4, title: 'Scandinavia',img:'/assets/images/cat-scan.jpg',tours:4, isHovered: false },
    { id: 5, title: 'America',img:'/assets/images/cat-america.jpg',tours:3, isHovered: false },
    { id: 6, title: 'Morocco',img:'/assets/images/cat-morocco.jpg',tours:10, isHovered: false }
    
  ];

  constructor(private router:Router){}


  onCardHover(index: number) {
    this.cards[index].isHovered = true;
  }

 
  onCardLeave(index: number) {
    this.cards[index].isHovered = false;
  }






  goToDestinations()
  {
    this.router.navigate(['/destinations']);
  }
}
