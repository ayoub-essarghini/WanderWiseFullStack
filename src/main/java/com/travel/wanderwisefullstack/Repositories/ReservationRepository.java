package com.travel.wanderwisefullstack.Repositories;

import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Reservation;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUsername(String username);
    boolean existsByUserAndTripAndCancelledFalse(AppUser user, Trip trip);
}
