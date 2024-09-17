import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8082/auth/login';
  private isLogged = false;
  constructor(private http: HttpClient) { }

  login(username: string, password: string)
  {
    let options =
      {
      headers: new HttpHeaders().set("Content-type","application/x-www-form-urlencoded")
      }
      let params = new HttpParams().set("username",username).set("password",password);
    return this.http.post(this.baseUrl,params,options).subscribe({
      next: value => {
        console.log(value);
        this.isLogged= true;
      },
      error: err => {
        console.log(err)
        this.isLogged = false;
      }
    });
  }

  isLoggedIn()
  {
    return this.isLogged;
  }


}
