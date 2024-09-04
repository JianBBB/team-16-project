package com.sparta.ordersystem.order.management.payment.repository;

import com.sparta.ordersystem.order.management.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>,PaymentRepositoryCustom {
}
