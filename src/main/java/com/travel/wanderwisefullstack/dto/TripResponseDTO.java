package com.travel.wanderwisefullstack.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TripResponseDTO {
    private Long id;
    private String destination;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private Integer duration;
    private Integer spots;
    private String itinerary;
    private List<ProgramDTO> programs;
}
