package com.travel.wanderwisefullstack.Services.reservation;

import com.travel.wanderwisefullstack.Repositories.ReservationRepository;
import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Reservation;
import com.travel.wanderwisefullstack.models.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public String bookTrip(Long tripId, String username) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new NoSuchElementException("Trip not found"));

        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }

        // Optional: prevent double booking
        boolean alreadyBooked = reservationRepository
            .existsByUserAndTripAndCancelledFalse(user, trip);
        if (alreadyBooked) {
            throw new IllegalStateException("You have already booked this trip.");
        }

        Reservation res = new Reservation();
        res.setTrip(trip);
        res.setUser(user);
        res.setCancelled(false);
        res.setReservedAt(LocalDateTime.now());

        reservationRepository.save(res);
        return "Trip booked successfully!";
    }

    public String cancelReservation(Long reservationId, String username) throws AccessDeniedException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found"));

        if (!reservation.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You can't cancel someone else's reservation.");
        }

        if (reservation.isCancelled()) {
            return "Reservation already cancelled.";
        }

        reservation.setCancelled(true);
        reservationRepository.save(reservation);
        return "Reservation cancelled successfully.";
    }

    public List<Reservation> getMyReservations(String username) {
        return reservationRepository.findByUserUsername(username);
    }
}
