import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Trip} from "../module/trip.module";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8082/api/trips'; // Replace with your API endpoint


  // Fetch trips data from the server
  getTrips(): Observable<Trip[]> {
    const token = localStorage.getItem('access_token');
    // console.log('Access Token:', token);  // Log token for debugging

    const headers = new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : '', // Add token to header
    });

    return this.http.get<Trip[]>(this.apiUrl, { headers });
  }
}
