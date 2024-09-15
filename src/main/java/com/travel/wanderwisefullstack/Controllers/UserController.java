package com.travel.wanderwisefullstack.Controllers;

import com.travel.wanderwisefullstack.Services.UserService;
import com.travel.wanderwisefullstack.models.Trip;
import com.travel.wanderwisefullstack.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}/trips")
    public ResponseEntity<List<Trip>> getUserTrips(@PathVariable String username) {
        List<Trip> trips = userService.getTripsByUser(username);
        return ResponseEntity.ok(trips);
    }

    @PostMapping("/reserve/{tripId}")
    public ResponseEntity<Trip> reserveTrip(@PathVariable Long tripId, @RequestParam Long userId) {
        Trip reservedTrip = userService.reserveTrip(tripId, userId);
        return ResponseEntity.ok(reservedTrip);
    }

    @PostMapping("/cancel/{tripId}")
    public ResponseEntity<Trip> cancelTrip(@PathVariable Long tripId, @RequestParam Long userId) {
        Trip canceledTrip = userService.cancelTrip(tripId, userId);
        return ResponseEntity.ok(canceledTrip);
    }
}
