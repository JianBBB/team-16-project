package com.sparta.ordersystem.order.management.payment.dto;


import com.sparta.ordersystem.order.management.order.entity.Order;
import com.sparta.ordersystem.order.management.payment.entity.Payment;
import com.sparta.ordersystem.order.management.payment.entity.PaymentMethod;
import com.sparta.ordersystem.order.management.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreatePaymentRequestDto {
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private UUID orderId;
    private Integer price;

    // DTO -> 엔티티 변환 메서드
    public Payment toEntity(Order order) { // Order 객체만 인자로 받음
        return Payment.builder()
                .method(this.paymentMethod)
                .status(this.paymentStatus)
                .total_price(this.price)
                .order(order)
                .build();
    }
}
