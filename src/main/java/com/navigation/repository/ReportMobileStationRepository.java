package com.navigation.repository;

import com.navigation.entity.ReportMobileStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportMobileStationRepository extends JpaRepository<ReportMobileStationEntity, Long> {
    List<ReportMobileStationEntity> findByMobileStationIdOrderByTimestamp(UUID mobileId);
}
