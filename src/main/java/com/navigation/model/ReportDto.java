package com.navigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private UUID baseStationId;
    private Set<ReportMobileStationRecordDto> reports;
}