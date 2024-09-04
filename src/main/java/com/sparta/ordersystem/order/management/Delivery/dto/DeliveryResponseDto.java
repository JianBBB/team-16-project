package com.sparta.ordersystem.order.management.delivery.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class DeliveryResponseDto {

    UUID delivery_id;
    String address;
    String requset_note;
    UUID order_id;
    boolean isActive;
    LocalDateTime created_at;
    LocalDateTime updated_at;

    public boolean getIsActive(){
        return this.isActive;
    }
}
