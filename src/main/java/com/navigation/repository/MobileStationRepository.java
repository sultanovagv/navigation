package com.navigation.repository;

import com.navigation.entity.MobileStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MobileStationRepository extends JpaRepository<MobileStationEntity, UUID> {

}
