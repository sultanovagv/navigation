package com.navigation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileStationDto {
    private UUID mobileStationId;
    private float x;
    private float y;
    private float errorRadius;
    private int errorCode;
    private String errorDescription;
}
