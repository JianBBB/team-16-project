package com.sparta.ordersystem.order.management.menu.repository;

import com.sparta.ordersystem.order.management.menu.entity.Menu;
import com.sparta.ordersystem.order.management.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {

    Optional<Menu> findByMenuIdAndIsActiveTrue(UUID id);
    List<Menu> findByStoreAndIsActiveTrue(Store store);
    Optional<Menu> findByMenuIdAndIsActiveTrueAndStore(UUID menu_id, Store store);

}
