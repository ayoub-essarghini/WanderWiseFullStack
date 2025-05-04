package com.travel.wanderwisefullstack.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProgramDTO {
    private String dayTitle;
    private String description;
    private List<SpotDTO> spots;
}
