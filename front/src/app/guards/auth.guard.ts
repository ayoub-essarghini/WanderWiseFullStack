import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import {AuthService} from "../services/auth.service";


export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService); // Inject the AuthService
  const router = inject(Router);          // Inject the Router

  if (authService.isAuthenticated()) {
    return true; // Allow access
  }

  // Redirect to login if not authenticated
  return router.createUrlTree(['/login'], { queryParams: { returnUrl: state.url } });
};
