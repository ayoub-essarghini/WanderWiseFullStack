import { Component, HostListener, OnInit } from '@angular/core';
import { NgClass, NgForOf, NgIf, NgStyle, UpperCasePipe } from "@angular/common";
import { Router, RouterLink, RouterLinkActive } from "@angular/router";
import {CommonModule} from "@angular/common";
import {AuthService} from "../../services/auth.service";
import { AvatarModule } from 'primeng/avatar';
import { OverlayModule } from 'primeng/overlay';
import {UserModule} from "../../module/user.module";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgForOf,
    NgStyle,
    NgClass,
    RouterLink,
    AvatarModule,
    CommonModule,
    OverlayModule

  ],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  links: string[] = ['home', 'trips', 'destinations', 'about-us'];
  activeIndex: number = -1;
  hoveredIndex: number = -1;

  constructor(private router: Router, private authService: AuthService,private messageService:MessageService) {}
  isScrolled:boolean = false;
  user!:UserModule;
  ngOnInit() {
    // Determine active link based on the current route
    // user = this.authService.loadProfile()
    this.links.forEach((link, index) => {
      if (this.router.url.includes(link)) {
        this.activeIndex = index;
      }
    });
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {

    if (window.scrollY > 100) {
     this.isScrolled = true;
    } else {
      this.isScrolled = false;
    }
  }

  isAuth(): boolean
  {
      return this.authService.isAuthenticated();
  }

  getActiveIndex(): number {
    return this.links.findIndex(link => this.router.url.includes(link));
  }


  onHovered(index: number) {
    this.hoveredIndex = index;

  }

  // setActiveLink(index: number) {
  //   this.activeIndex = index;
  // }

  clearHover() {
    this.hoveredIndex = -1;
  }

  isActiveLink(link: string): boolean {
    return this.router.url.includes(link);
  }

  getDotPosition(): string {
    // Adjust dot position for hover or active states
    if (this.hoveredIndex !== -1) {
      return `${(this.hoveredIndex * 100) / this.links.length + 2}%`;
    }
    if (this.activeIndex !== -1) {
      return `${(this.activeIndex * 100) / this.links.length + 2}%`;
    }
    return `${(this.getActiveIndex() * 100) / this.links.length + 2}%`;
  }

  goHome() {
    this.router.navigate(['/home']);
    this.clearHover()
    this.activeIndex = 0;
  }


  handleLogOut() {

    this.authService.logout()
    this.messageService.add({ severity: "success", summary: "Logout", detail: "Logout successfully!", life: 3000 });

  }
}
