package com.sparta.ordersystem.order.management.order.dto;

import com.sparta.ordersystem.order.management.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStateRequestDto {

    private OrderStatus orderStatus;
}
