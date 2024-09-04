package com.sparta.ordersystem.order.management.order.dto;

import com.sparta.ordersystem.order.management.order.entity.Order;
import com.sparta.ordersystem.order.management.order.entity.OrderStatus;
import com.sparta.ordersystem.order.management.order.entity.OrderType;
import com.sparta.ordersystem.order.management.store.entity.Store;
import com.sparta.ordersystem.order.management.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequestDto {
    OrderType orderType;//대면 or 온라인 주문
    List<UUID> menu_ids;
    UUID store_id;

    public Order toEntity(User user, Store store) {
        return Order.builder()
                .user(user)
                .orderType(orderType)
                .store(store)
                .orderStatus(OrderStatus.CREATE)
                .build();
    }
}
