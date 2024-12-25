package com.travel.wanderwisefullstack.Services.trip;

import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.dto.TripDTO;
import com.travel.wanderwisefullstack.mapper.TripMapper;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TripServices {

    private final TripRepository tripRepository;
    private final UserRepository accountService;
    private  TripMapper tripMapper;

    public TripServices(TripRepository tripRepository, UserRepository accountService, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.accountService = accountService;

        this.tripMapper = tripMapper;
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
    public TripDTO createTrip(Trip trip)
    {


        return tripMapper.fromTrip(tripRepository.save(trip));
    }

    public void deleteTrip(Long id)
    {
        tripRepository.deleteById(id);
    }

}
