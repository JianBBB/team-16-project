package com.sparta.ordersystem.order.management.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderRepsoitoryCustom {
    Optional<Order> findByOrderIdAndIsActiveTrue(UUID orderId);
}
