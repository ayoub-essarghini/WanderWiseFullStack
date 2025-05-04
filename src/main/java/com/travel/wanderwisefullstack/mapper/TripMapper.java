package com.travel.wanderwisefullstack.mapper;

import com.travel.wanderwisefullstack.dto.TripRequestDTO;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TripMapper {


    public TripRequestDTO fromTrip(Trip trip) {
        TripRequestDTO tripDTO = new TripRequestDTO();
        BeanUtils.copyProperties(trip, tripDTO);
        return tripDTO;
    }

    public Trip fromTripDto(TripRequestDTO tripDTO) {
        Trip trip = new Trip();
        BeanUtils.copyProperties(tripDTO, trip);
        return trip;
    }
}
