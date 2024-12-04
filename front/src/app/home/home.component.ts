import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router} from "@angular/router";
import { CarouselModule } from 'primeng/carousel';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from "../partials/footer/footer.component"; 
import { CardModule } from 'primeng/card';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CarouselModule,
    FormsModule,
    CardModule,
    RatingModule,
    FooterComponent
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

  responsiveOptions = [
    {
      breakpoint: '1024px',
      numVisible: 3,
      numScroll: 1
    },
    {
      breakpoint: '768px',
      numVisible: 2,
      numScroll: 1
    },
    {
      breakpoint: '560px',
      numVisible: 1,
      numScroll: 1
    }
  ];
  

  
  carousel_items = [
    { image: 'assets/slides/slide1.jpg',title:'Two Moscow Tour of 7 days',subtitle:'8 Days 7 Nights', oldPrice:'$2,600',currentPrice:'$2,100', rate:5,reviews:1 },
    { image: 'assets/slides/slide2.jpg',title:'Austria – 6 Days in Vienna, Hallstatt',subtitle:'2 Days 1 Night', oldPrice:'$3,880',currentPrice:'$3,500', rate:5,reviews:3 },
    { image: 'assets/slides/slide3.jpg',title:'Argentina – Great Diving Trip',subtitle:'2 Days 1 Nights', oldPrice:'',currentPrice:'$1,200', rate:5,reviews:2 },
    { image: 'assets/slides/slide4.jpg',title:'India – Mumbai, New Delhi',subtitle:'5 Days 4 Nights', oldPrice:'$2,100',currentPrice:'$1,600', rate:4,reviews:5 },
    { image: 'assets/slides/slide5.jpg',title:'America – Grand canyon, Golden Gate',subtitle:'9 Days 8 Nights', oldPrice:'$2,400',currentPrice:'$2,800', rate:3.5,reviews:6 },
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
