import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router} from "@angular/router";
import { CarouselComponent } from "../partials/carousel/carousel.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CarouselComponent
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

  
  carousel_items = [
    { image: 'assets/slides/slide1.jpg' },
    { image: 'assets/slides/slide2.jpg'},
    { image: 'assets/slides/slide3.jpg'},
    { image: 'assets/slides/slide4.jpg'},
    { image: 'assets/slides/slide5.jpg'},
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
