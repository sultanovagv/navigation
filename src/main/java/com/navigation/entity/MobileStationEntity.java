package com.navigation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mobile_station")
public class MobileStationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mobile_station")
    @SequenceGenerator(name = "seq_mobile_station", sequenceName = "seq_mobile_station", allocationSize = 1)
    private UUID id;

    private float lastKnownX;

    private float lastKnownY;

    private LocalDate createDate;
}
