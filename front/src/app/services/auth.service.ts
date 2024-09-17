import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8082/auth/login';
  private loggedIn = false;
  constructor(private http: HttpClient) { }

  login(username: string, password: string):Observable<boolean>
  {
    return this.http.post<any>(`${this.baseUrl}/login`,{username,password}).pipe(
      map(response => {
        if (response.token)
        {
          localStorage.setItem('token',response.token);
          this.loggedIn = true;
          return true;
        }
        return false;
      }),
      catchError(() => of(false))
    );
  }

  isLoggedIn(): boolean {
    return this.loggedIn && !!localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn = false;
  }
}
