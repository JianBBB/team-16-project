package com.sparta.ordersystem.order.management.ai.repository;

import com.sparta.ordersystem.order.management.ai.entity.Ai;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiRepository extends JpaRepository<Ai, UUID> {
}
