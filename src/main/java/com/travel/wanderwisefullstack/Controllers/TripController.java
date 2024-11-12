package com.travel.wanderwisefullstack.Controllers;

import com.travel.wanderwisefullstack.Services.trip.TripServices;
import com.travel.wanderwisefullstack.models.Trip;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@AllArgsConstructor

public class TripController {

    private final TripServices tripServices;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripServices.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    @Secured({"ADMIN","USER"})
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip =  tripServices.getTripById(id);
        return ResponseEntity.ok(trip);
    }

    @PostMapping("/book/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> bookTrip(@PathVariable Long id) {
        try {
            tripServices.bookTrip(id); // Service logic to book the trip
            return ResponseEntity.ok("Trip successfully booked.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while booking the trip.");
        }
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity < Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripServices.createTrip(trip);
        return ResponseEntity.ok(createdTrip);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTrip(@PathVariable Long id) {
        tripServices.deleteTrip(id);
    }
}
