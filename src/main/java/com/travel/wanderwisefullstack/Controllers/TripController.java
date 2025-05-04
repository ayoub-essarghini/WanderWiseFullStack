package com.travel.wanderwisefullstack.Controllers;

import com.travel.wanderwisefullstack.Services.trip.TripService;
import com.travel.wanderwisefullstack.dto.TripRequestDTO;
import com.travel.wanderwisefullstack.dto.TripResponseDTO;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@AllArgsConstructor

public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripResponseDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @DeleteMapping("/{tripId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok("Trip deleted successfully.");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTrip(@RequestBody TripRequestDTO dto) {
        return ResponseEntity.ok(tripService.createTrip(dto));
    }

    @PutMapping("/{tripId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTrip(@PathVariable Long tripId, @RequestBody TripRequestDTO dto) {
        return ResponseEntity.ok(tripService.updateTrip(tripId, dto));
    }




}
