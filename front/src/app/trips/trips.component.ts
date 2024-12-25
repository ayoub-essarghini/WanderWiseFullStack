import {Component, OnInit} from '@angular/core';
import {Trip} from "../module/trip.module";
import {TripService} from "../services/trip.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-trips',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trips.component.html',
  styleUrl: './trips.component.css'
})
export class TripsComponent implements OnInit{

  constructor(private tripService:TripService) {
  }
  trips:Trip[] = [];
    ngOnInit(): void {
      this.tripService.getTrips().subscribe(
        (data: Trip[]) => {
          this.trips = data; // Set the trips data into the model
        },
        (error) => {
          console.error('Error fetching trips', error);
        }
      );
    }


}
