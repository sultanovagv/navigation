package com.navigation.controller;

import com.navigation.model.MobileStationDto;
import com.navigation.model.MobileStationRequest;
import com.navigation.model.ReportDto;
import com.navigation.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping("/location-report")
    public ResponseEntity<Void> createBaseStationReport(@RequestBody ReportDto reportDto) {
        stationService.createBaseStationReport(reportDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/location/{mobileStationId}")
    public MobileStationDto getMobileStationLocation(@RequestBody MobileStationRequest dto) {
        return stationService.getMobileStationLocation(dto);
    }

}

