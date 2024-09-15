package com.travel.wanderwisefullstack.Services;

import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.models.Trip;
import com.travel.wanderwisefullstack.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Trip reserveTrip(Long tripId,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        Trip trip = tripRepository.findById(tripId).orElseThrow(()->new RuntimeException("Trip Not Found"));
        trip.setUser(user);
        return tripRepository.save(trip);
    }

    public Trip cancelTrip(Long tripId,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        Trip trip = tripRepository.findById(tripId).orElseThrow(()->new RuntimeException("Trip Not Found"));
        trip.setUser(null);
        return tripRepository.save(trip);
    }

    public List<Trip> getTripsByUser(String username) {
        User user = userRepository.findByUsername(username);
        return tripRepository.findByUser(user);
    }
}

