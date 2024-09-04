package com.sparta.ordersystem.order.management.store.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StoreDeleteResponseDto {

    private UUID storeId;
    private String storeName;
    private boolean isActive;


    @Builder
    public StoreDeleteResponseDto(UUID storeId, String storeName, boolean isActive){
        this.storeId = storeId;
        this.storeName = storeName;
        this.isActive = isActive;
    }
}
