package com.travel.wanderwisefullstack.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         // e.g., "Desert Walk"
    private String description;  // Details about the spot

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;
}
