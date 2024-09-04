package com.sparta.ordersystem.order.management.order.dto;

import com.sparta.ordersystem.order.management.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderSearchDto {
    OrderStatus orderStatus;
    Boolean isActive;
}
