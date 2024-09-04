package com.sparta.ordersystem.order.management.payment.dto;

import com.sparta.ordersystem.order.management.payment.entity.PaymentMethod;
import com.sparta.ordersystem.order.management.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PaymentResponseDto {

    private UUID paymentId;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private Integer totalPrice;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
