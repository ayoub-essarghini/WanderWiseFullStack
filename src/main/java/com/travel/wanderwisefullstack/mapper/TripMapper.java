package com.travel.wanderwisefullstack.mapper;

import com.travel.wanderwisefullstack.dto.TripDTO;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TripMapper {


    public TripDTO fromTrip(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        BeanUtils.copyProperties(trip, tripDTO);
        return tripDTO;
    }

    public Trip fromTripDto(TripDTO tripDTO) {
        Trip trip = new Trip();
        BeanUtils.copyProperties(tripDTO, trip);
        return trip;
    }
}
