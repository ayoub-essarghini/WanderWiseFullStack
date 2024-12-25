import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8082/login';

  username: any;
  accessToken !: string;
  refreshToken !: string;

  roles: any;

  constructor(private http: HttpClient, private router: Router) {
  }

  login(username: string, password: string) {
    let options =
      {
        headers: new HttpHeaders().set("Content-type", "application/x-www-form-urlencoded")
      }
    let params = new HttpParams().set("username", username).set("password", password);
    return this.http.post(this.baseUrl, params, options);
  }

  loadProfile(data: any) {
    this.accessToken = data["access_token"];
    this.refreshToken = data["refresh_token"];

    if (this.accessToken && this.refreshToken) {
      this.storeTokensInSecureStorage(this.accessToken, this.refreshToken);

      let jwtDecoder: any = jwtDecode(this.accessToken);
      this.username = jwtDecoder.sub;
      this.roles = jwtDecoder.roles;
    } else {
      console.error("Access Token or Refresh Token is missing.");
    }
  }

// Method to store tokens securely
  private storeTokensInSecureStorage(accessToken: string, refreshToken: string) {
    // Store access token in memory if possible
    localStorage.setItem("access_token", accessToken);

    // Store refresh token securely in an HttpOnly and Secure cookie
    this.setSecureCookie("refresh_token", refreshToken);
  }

// Method to set an HttpOnly, Secure cookie
  private setSecureCookie(name: string, value: string) {
    document.cookie = `${name}=${encodeURIComponent(value)}; path=/; HttpOnly; Secure; SameSite=Strict`;
  }

// Method to retrieve tokens securely
  private getAccessToken(): string | null {
    return localStorage.getItem("access_token");
  }

  private getRefreshToken(): string | null {
    const name = "refresh_token=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const cookiesArray = decodedCookie.split(";");
    for (let i = 0; i < cookiesArray.length; i++) {
      let c = cookiesArray[i];
      while (c.charAt(0) === " ") {
        c = c.substring(1);
      }
      if (c.indexOf(name) === 0) {
        return c.substring(name.length, c.length);
      }
    }
    return null;
  }


  isAuthenticated(): boolean {

    const token = this.getAccessToken();
    return !!token;
  }

  logout(): void {

    localStorage.removeItem('access_token');

    this.router.navigate(['/login']);
  }


}
