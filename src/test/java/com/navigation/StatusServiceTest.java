package com.navigation;

import com.navigation.entity.BaseStationEntity;
import com.navigation.entity.MobileStationEntity;
import com.navigation.model.MobileStationRequest;
import com.navigation.model.ReportDto;
import com.navigation.model.ReportMobileStationRecordDto;
import com.navigation.repository.BaseStationRepository;
import com.navigation.repository.MobileStationRepository;
import com.navigation.repository.ReportMobileStationRepository;
import com.navigation.service.StationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class StatusServiceTest {

    private static final UUID MOBILE_ID = UUID.randomUUID();

    @Autowired
    private ReportMobileStationRepository reportMobileStationRepository;

    @Autowired
    private BaseStationRepository baseStationRepository;

    @Autowired
    private MobileStationRepository mobileStationRepository;

    @Autowired
    private StationService stationService;


    @Test
    void createBaseStationReport() {
        var baseStation = saveBaseStation(MOBILE_ID, 3, 4);
        stationService.createBaseStationReport(baseStation);

        assertThat(1).isEqualTo(reportMobileStationRepository.findAll().size());
    }

    @Test
    void getMobileStationById() {
        var mobileStation = mobileStationRepository.save(MobileStationEntity.builder()
                .lastKnownY(4)
                .lastKnownX(3)
                .createDate(LocalDate.now()).build());
        var request = new MobileStationRequest(mobileStation.getId(), LocalDate.now());
        var baseStation1 = saveBaseStation(mobileStation.getId(), 4, 5);
        var baseStation2 = saveBaseStation(mobileStation.getId(), 6, 7);
        stationService.createBaseStationReport(baseStation1);
        stationService.createBaseStationReport(baseStation2);

        var result = stationService.getMobileStationLocation(request);
        System.out.println(result);
    }

    private ReportDto saveBaseStation(UUID mobileId, float x, float y) {
        var date = LocalDate.of(2023, 5, 8);
        var records = new HashSet<ReportMobileStationRecordDto>();
        records.add(new ReportMobileStationRecordDto(mobileId, 5, date));
        var baseStation = baseStationRepository.save(BaseStationEntity.builder()
                .name("Base1")
                .x(x)
                .y(y)
                .detectionRadiusInMeters(10)
                .build());
        return new ReportDto(baseStation.getId(), records);
    }
}
