package com.travel.wanderwisefullstack.Services.trip;

import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TripServices {

    private final TripRepository tripRepository;
    private final UserRepository accountService;

    public TripServices(TripRepository tripRepository, UserRepository accountService) {
        this.tripRepository = tripRepository;
        this.accountService = accountService;
    }

    public List<Trip> getAllTrips()
    {
        return tripRepository.findAll();
    }
    public void bookTrip(Long id)
    {
        if (tripRepository.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();
            Trip trip = tripRepository.findById(id).get();
            AppUser user = accountService.findByUsername(currentUsername);
            user.getTrips().add(trip);
            user.setTrips(user.getTrips());
            accountService.save(user);
            tripRepository.save(trip);


        }

    }


    public Trip getTripById(Long id)
    {
        return tripRepository.findById(id).orElseThrow(()->new RuntimeException("Trip not found"));
    }
    public Trip createTrip(Trip trip)
    {

        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id)
    {
        tripRepository.deleteById(id);
    }

}
