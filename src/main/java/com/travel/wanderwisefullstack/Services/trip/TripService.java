package com.travel.wanderwisefullstack.Services.trip;

import com.travel.wanderwisefullstack.Repositories.TripRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.dto.ProgramDTO;
import com.travel.wanderwisefullstack.dto.SpotDTO;
import com.travel.wanderwisefullstack.dto.TripRequestDTO;
import com.travel.wanderwisefullstack.dto.TripResponseDTO;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Program;
import com.travel.wanderwisefullstack.models.Spot;
import com.travel.wanderwisefullstack.models.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public TripResponseDTO createTrip(TripRequestDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        AppUser user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setDescription(dto.getDescription());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setPrice(dto.getPrice());
        trip.setDuration(dto.getDuration());
        trip.setSpots(dto.getSpots());
        trip.setItinerary(dto.getItinerary());
        trip.setUser(user);

        List<Program> programs = new ArrayList<>();
        for (ProgramDTO programDTO : dto.getPrograms()) {
            Program program = new Program();
            program.setDayTitle(programDTO.getDayTitle());
            program.setDescription(programDTO.getDescription());
            program.setTrip(trip);

            List<Spot> spots = new ArrayList<>();
            for (SpotDTO spotDTO : programDTO.getSpots()) {
                Spot spot = new Spot();
                spot.setName(spotDTO.getName());
                spot.setDescription(spotDTO.getDescription());
                spot.setProgram(program);
                spots.add(spot);
            }
            program.setSpots(spots);
            programs.add(program);
        }

        trip.setPrograms(programs);
        tripRepository.save(trip);
        return convertToResponse(trip);
    }

    public List<TripResponseDTO> getAllTrips() {
        return tripRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private TripResponseDTO convertToResponse(Trip trip) {
        TripResponseDTO dto = new TripResponseDTO();
        dto.setId(trip.getId());
        dto.setDestination(trip.getDestination());
        dto.setDescription(trip.getDescription());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setPrice(trip.getPrice());
        dto.setDuration(trip.getDuration());
        dto.setSpots(trip.getSpots());
        dto.setItinerary(trip.getItinerary());

        List<ProgramDTO> programDTOs = trip.getPrograms().stream().map(program -> {
            ProgramDTO pDto = new ProgramDTO();
            pDto.setDayTitle(program.getDayTitle());
            pDto.setDescription(program.getDescription());

            List<SpotDTO> spotDTOs = program.getSpots().stream().map(spot -> {
                SpotDTO sDto = new SpotDTO();
                sDto.setName(spot.getName());
                sDto.setDescription(spot.getDescription());
                return sDto;
            }).collect(Collectors.toList());

            pDto.setSpots(spotDTOs);
            return pDto;
        }).collect(Collectors.toList());

        dto.setPrograms(programDTOs);
        return dto;
    }

    public TripResponseDTO updateTrip(Long tripId, TripRequestDTO dto) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new NoSuchElementException("Trip not found"));

        trip.setDestination(dto.getDestination());
        trip.setDescription(dto.getDescription());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setPrice(dto.getPrice());
        trip.setDuration(dto.getDuration());
        trip.setSpots(dto.getSpots());
        trip.setItinerary(dto.getItinerary());

        // Clear and rebuild programs
        trip.getPrograms().clear();
        for (ProgramDTO programDTO : dto.getPrograms()) {
            Program program = new Program();
            program.setDayTitle(programDTO.getDayTitle());
            program.setDescription(programDTO.getDescription());
            program.setTrip(trip);

            List<Spot> spots = new ArrayList<>();
            for (SpotDTO spotDTO : programDTO.getSpots()) {
                Spot spot = new Spot();
                spot.setName(spotDTO.getName());
                spot.setDescription(spotDTO.getDescription());
                spot.setProgram(program);
                spots.add(spot);
            }

            program.setSpots(spots);
            trip.getPrograms().add(program);
        }

        tripRepository.save(trip);
        return convertToResponse(trip);
    }

    public void deleteTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new NoSuchElementException("Trip not found"));

        tripRepository.delete(trip);
    }


}
