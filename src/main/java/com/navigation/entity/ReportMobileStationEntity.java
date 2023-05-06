package com.navigation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "report_mobile_station")
public class ReportMobileStationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_mobile_station")
    @SequenceGenerator(name = "seq_report_mobile_station", sequenceName = "seq_report_mobile_station", allocationSize = 1)
    private UUID reportId;

    @Column(name = "base_station_id")
    private UUID baseStationId;

    @Column(name = "mobile_station_id")
    private UUID mobileStationId;

    private float distance;

    private Timestamp timestamp;

}
