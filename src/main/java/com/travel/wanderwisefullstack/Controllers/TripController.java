package com.travel.wanderwisefullstack.Controllers;

import com.travel.wanderwisefullstack.Services.TripServices;
import com.travel.wanderwisefullstack.models.Trip;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@AllArgsConstructor

public class TripController {

    private final TripServices tripServices;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripServices.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip =  tripServices.getTripById(id);
        return ResponseEntity.ok(trip);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity < Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripServices.createTrip(trip);
        return ResponseEntity.ok(createdTrip);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteTrip(@PathVariable Long id) {
        tripServices.deleteTrip(id);
    }
}
