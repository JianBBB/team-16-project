package com.sparta.ordersystem.order.management.region.repository;

import com.sparta.ordersystem.order.management.region.entity.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    boolean existsByRegionNameAndIsActiveTrue(String regionName);
    List<Region> findAllByIsActiveTrue(Pageable pageable);
    Optional<Region> findByRegionIdAndIsActiveTrue(UUID regionId);
}
