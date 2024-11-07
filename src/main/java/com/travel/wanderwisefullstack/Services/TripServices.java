package com.travel.wanderwisefullstack.Services;

import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServices {

    private final TripRepository tripRepository;

    public TripServices(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getAllTrips()
    {
        return tripRepository.findAll();
    }

    public List<Trip> getUserTrips(AppUser user)
    {
        return tripRepository.findByUser(user);
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
