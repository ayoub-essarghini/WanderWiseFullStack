import { Component, HostListener, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carousel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css',
})
export class CarouselComponent implements OnInit {
  currentIndex = 0;
  cardWidth: number = 0;
  slide_active = true;
  startIndex = 0;
  maxVisible = 3;
  private autoSlideInterval: any;
  @Input() slides: { image: string }[] = [];
  ngOnInit() {
    // this.startAutoSlide();
    console.log(this.slide_active);
    this.calculateCardWidth();
  }

  next() {
    const maxIndex = Math.ceil(this.slides.length) - 3;
    if (this.currentIndex < maxIndex) {
      this.currentIndex++;
    } else this.currentIndex = 0;
  }

  prev() {
    if (this.currentIndex > 0) {
      this.currentIndex--;
    }
  }

  startAutoSlide() {
    this.autoSlideInterval = setInterval(() => {
      if (this.slide_active) this.next();
    }, 3000);
  }

  stopAutoSlide() {
    clearInterval(this.autoSlideInterval);
  }

  onMouseEnter() {
    this.slide_active = false;
    this.stopAutoSlide(); // Stop auto-slide when hovering
  }

  onMouseLeave() {
    this.slide_active = true;
    this.startAutoSlide(); // Resume auto-slide when no longer hovering
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.calculateCardWidth();
  }

  private calculateCardWidth(): void {
    const containerWidth = window.innerWidth * 0.7; // Get the current width of the viewport or a specific container
    const cardsPerRow = 3; // Number of cards you want per row
    this.cardWidth = containerWidth / cardsPerRow; // Adjust this to calculate card width dynamically
  }
}
