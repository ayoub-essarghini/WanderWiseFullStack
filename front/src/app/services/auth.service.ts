import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8082/auth/login';

  username:any;
  accessToken !:string;
  isAuthenticated : boolean = false;
  roles:any;

  constructor(private http: HttpClient) { }

  login(username: string, password: string)
  {
    let options =
      {
      headers: new HttpHeaders().set("Content-type","application/x-www-form-urlencoded")
      }
      let params = new HttpParams().set("username",username).set("password",password);
    return this.http.post(this.baseUrl,params,options);
  }

  loadProfile(data:any)
  {
    this.isAuthenticated  = true;
    this.accessToken = data["access_token"];
    localStorage.setItem("access_token", this.accessToken);
    let jwtDecoder:any = jwtDecode(this.accessToken);
    this.username = jwtDecoder.sub;
    this.roles = jwtDecoder.scope;

  }




}
