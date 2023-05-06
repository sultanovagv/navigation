package com.navigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportMobileStationRecordDto {

    private UUID mobileStationId;
    private float distance;
    private LocalDateTime timestamp;
}
