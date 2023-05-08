package com.navigation.repository;

import com.navigation.entity.BaseStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BaseStationRepository extends JpaRepository<BaseStationEntity, UUID> {
    Optional<BaseStationEntity> findById(UUID uuid);
    List<BaseStationEntity> findAllByIdIn(List<UUID> ids);

}
