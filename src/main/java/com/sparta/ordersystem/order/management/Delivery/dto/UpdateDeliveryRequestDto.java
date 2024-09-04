package com.sparta.ordersystem.order.management.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeliveryRequestDto {

    String address;
    String request_note;
}
