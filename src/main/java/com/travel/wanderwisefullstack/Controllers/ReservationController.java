package com.travel.wanderwisefullstack.Controllers;

import com.travel.wanderwisefullstack.Services.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/book/{tripId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookTrip(@PathVariable Long tripId, Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(reservationService.bookTrip(tripId, username));
    }

    @DeleteMapping("/{reservationId}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> cancelTrip(@PathVariable Long reservationId, Authentication auth) throws AccessDeniedException {
        String username = auth.getName();
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId, username));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getMyReservations(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(reservationService.getMyReservations(username));
    }
}
