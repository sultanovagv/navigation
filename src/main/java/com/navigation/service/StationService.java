package com.navigation.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.navigation.entity.BaseStationEntity;
import com.navigation.entity.MobileStationEntity;
import com.navigation.entity.ReportMobileStationEntity;
import com.navigation.error.BaseStationNotFoundException;
import com.navigation.error.MobileStationNotFoundException;
import com.navigation.model.MobileStationDto;
import com.navigation.model.MobileStationRequest;
import com.navigation.model.ReportDto;
import com.navigation.model.ReportMobileStationRecordDto;
import com.navigation.repository.BaseStationRepository;
import com.navigation.repository.MobileStationRepository;
import com.navigation.repository.ReportMobileStationRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StationService {

    private final ReportMobileStationRepository reportMobileStationRepository;
    private final BaseStationRepository baseStationRepository;
    private final MobileStationRepository mobileStationRepository;

    public void createBaseStationReport(ReportDto reportDto) {
        var reportMobileStationEntities = new ArrayList<ReportMobileStationEntity>();
        reportDto.getReports().forEach(report ->
                checkDetectionDistanceOfMobileState(reportDto.getBaseStationId(), reportMobileStationEntities, report));
        var reportMobileStationEntities1 = reportMobileStationRepository.saveAll(reportMobileStationEntities);
        System.out.println(reportMobileStationEntities1);
    }

    public MobileStationDto getMobileStationLocation(MobileStationRequest request) {
        var reports = reportMobileStationRepository
                .findByMobileStationIdAndTimestamp(request.getMobileStationId(),
                        Timestamp.valueOf(request.getReportDate().atStartOfDay()));

        if (reports.size() == 0) {
            throw new MobileStationNotFoundException(request.getMobileStationId());
        }
        var mobileStationDtoBuilder = MobileStationDto.builder().mobileStationId(request.getMobileStationId());
        var baseStations = getBaseStationEntities(reports);
        if (baseStations.size() == 1) {
            return mobileStationDtoBuilder
                    .errorCode(500)
                    .errorDescription("There is no enough report for calculation." +
                            " Need at least two report.")
                    .build();
        }
        double[] coordinates = getMobileStationCoordinates(reports, baseStations);
        var x = (float) coordinates[0];
        var y = (float) coordinates[1];

        mobileStationRepository.save(new MobileStationEntity(request.getMobileStationId(), x, y, LocalDate.now()));
        var baseStation = baseStations.get(0);
        var distance = (float) Math.hypot(baseStation.getX() - x, baseStation.getY() - y);
        return mobileStationDtoBuilder
                .x(x)
                .y(y)
                .errorCode(200)
                .errorRadius(distance)
                .errorDescription("Calculation is ok!")
                .build();
    }

    private double[] getMobileStationCoordinates(List<ReportMobileStationEntity> reports, List<BaseStationEntity> baseStations) {
        double[][] coordinates = baseStations
                .stream()
                .map(key -> new double[]{key.getX(), key.getY()})
                .toArray(double[][]::new);

        double[] distances = reports.stream()
                .map(ReportMobileStationEntity::getDistance)
                .mapToDouble(Float::doubleValue)
                .toArray();

        var solve = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(coordinates, distances),
                new LevenbergMarquardtOptimizer()).solve();
        return solve.getPoint().toArray();
    }

    private List<BaseStationEntity> getBaseStationEntities(List<ReportMobileStationEntity> reports) {
        var baseStationIds = reports.stream()
                .map(ReportMobileStationEntity::getBaseStationId)
                .collect(Collectors.toList());
        return baseStationRepository.findAllByIdIn(baseStationIds);
    }

    private void checkDetectionDistanceOfMobileState(UUID baseStationId, List<ReportMobileStationEntity> reportStations,
                                                     ReportMobileStationRecordDto reportMobileStation) {
        var baseStation = baseStationRepository.findById(baseStationId)
                .orElseThrow(() -> new BaseStationNotFoundException(baseStationId));
        if (reportMobileStation.getDistance() <= baseStation.getDetectionRadiusInMeters()) {
            reportStations.add(ReportMobileStationEntity.builder()
                    .baseStationId(baseStationId)
                    .distance(reportMobileStation.getDistance())
                    .mobileStationId(reportMobileStation.getMobileStationId())
                    .timestamp(Timestamp.valueOf(reportMobileStation.getTimestamp().atStartOfDay()))
                    .build());
        }
    }

}