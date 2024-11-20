import {Component, OnInit} from '@angular/core';
import {NgClass, NgForOf, NgIf, NgStyle, UpperCasePipe} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {routes} from "../../app.routes";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgForOf,
    NgStyle,
    NgIf,
    NgClass,
    RouterLink,
    RouterLinkActive,
    UpperCasePipe
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  links:string[] = ['home', 'trips', 'destinations', 'about-us'];

  activeIndex: number = -1;
  hoveredIndex:number = -1;
  constructor(private router: Router) {}
  ngOnInit() {
    const storedActiveIndex = localStorage.getItem('activeLink');
    if (storedActiveIndex) {
      this.activeIndex = parseInt(storedActiveIndex, 10);
    }
    else
        this.activeIndex = 0;
  }



  onHovered(index: number)
  {
    this.hoveredIndex = index;
  }
  setActiveLink(current:number)
  {
    this.activeIndex = current;
    window.localStorage.setItem("activeLink",current.toString())
  }

  clearHover()
  {
    this.hoveredIndex = -1;
  }

  isActiveLink(link:string): boolean
  {
    return this.router.url.includes(link)
  }

  getDotPosition(): string {

    if (this.hoveredIndex !== -1) {
      return  `${(this.hoveredIndex * 100) / this.links.length + 5}%`;
    }
    if (this.activeIndex !== -1) {
      return `${(this.activeIndex * 100) / this.links.length + 5}%`;
    }
    return '5%';
  }


}
