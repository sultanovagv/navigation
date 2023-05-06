package com.navigation.controller;

import com.navigation.model.MobileStationDto;
import com.navigation.model.ReportDto;
import com.navigation.service.ReportStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StationController {

    private final ReportStationService reportStationService;

    @PostMapping("/location-report")
    public ResponseEntity<Void> createBaseStationReport(@RequestBody ReportDto reportDto) {
        reportStationService.createBaseStationReport(reportDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/location/{mobileStationId}")
    public MobileStationDto getMobileStationLocation(@PathVariable("mobileStationId") UUID mobileStationId) {
        return reportStationService.getMobileStationLocation(mobileStationId);
    }

}

