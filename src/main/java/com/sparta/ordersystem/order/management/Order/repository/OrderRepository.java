package com.sparta.ordersystem.order.management.order.repository;

import com.sparta.ordersystem.order.management.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderRepsoitoryCustom {
    Optional<Order> findByOrderIdAndIsActiveTrue(UUID orderId);
}
