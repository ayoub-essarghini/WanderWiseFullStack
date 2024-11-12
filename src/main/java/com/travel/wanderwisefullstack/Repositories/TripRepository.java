package com.travel.wanderwisefullstack.Repositories;

import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {


}
