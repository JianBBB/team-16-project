package com.sparta.ordersystem.order.management.payment.repository;

import com.sparta.ordersystem.order.management.payment.dto.PaymentResponseDto;

import java.util.List;

public interface PaymentRepositoryCustom {
    List<PaymentResponseDto> getAllPaymentsByUserId(Long userId);
}
