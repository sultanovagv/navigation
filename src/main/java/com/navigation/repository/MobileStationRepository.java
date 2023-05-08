package com.navigation.repository;

import com.navigation.entity.MobileStationEntity;
import com.navigation.entity.ReportMobileStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface MobileStationRepository extends JpaRepository<MobileStationEntity, UUID> {


}
