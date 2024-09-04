package com.sparta.ordersystem.order.management.delivery.repository;


import com.sparta.ordersystem.order.management.delivery.entity.Delivery;
import com.sparta.ordersystem.order.management.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    Optional<Delivery> findByDeliveryIdAndIsActiveTrue(UUID deliveryId);
    Optional<Delivery> findByOrderAndIsActiveTrue(Order order);
}
