package com.travel.wanderwisefullstack.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double price;

    private Integer duration;
    private Integer spots;

    @Lob
    private String itinerary;

    @ManyToOne
    private AppUser user;
}
