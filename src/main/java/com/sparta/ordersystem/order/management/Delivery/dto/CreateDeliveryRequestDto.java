package com.sparta.ordersystem.order.management.delivery.dto;

import com.sparta.ordersystem.order.management.delivery.entity.Delivery;
import com.sparta.ordersystem.order.management.order.entity.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateDeliveryRequestDto {

    UUID order_id;
    String address;
    String request_note;

    public Delivery toEntity(boolean isActive, Order order){
        return Delivery.builder()
                .address(address)
                .request_note(request_note)
                .isActive(isActive)
                .order(order)
                .build();
    }
}
