package com.sparta.ordersystem.order.management.payment.dto;

import com.sparta.ordersystem.order.management.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UpdateStatusRequestDto {
    private PaymentStatus paymentStatus;
}
