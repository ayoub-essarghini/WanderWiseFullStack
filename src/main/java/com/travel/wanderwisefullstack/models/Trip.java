package com.travel.wanderwisefullstack.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private Double price;

    private Integer duration;
    private Integer spots;

    @Lob
    private String itinerary;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users = new ArrayList<>();
}
