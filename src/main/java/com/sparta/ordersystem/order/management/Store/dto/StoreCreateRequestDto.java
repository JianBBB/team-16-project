package com.sparta.ordersystem.order.management.store.dto;


import com.sparta.ordersystem.order.management.store.entity.Store;
import com.sparta.ordersystem.order.management.user.entity.User;
import com.sparta.ordersystem.order.management.category.entity.Category;
import com.sparta.ordersystem.order.management.region.entity.Region;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class StoreCreateRequestDto {
    @NotBlank(message = "storeName cannot be blank")
    private String storeName;
    @NotNull(message = "categoryId cannot be blank")
    private UUID categoryId;
    @NotNull(message = "regionId cannot be blank")
    private UUID regionId;


    @Builder
    public StoreCreateRequestDto(String storeName, UUID categoryId, UUID regionId) {
        this.storeName = storeName;
        this.categoryId = categoryId;
        this.regionId = regionId;
    }

    // toEntity 메서드 추가
    public Store toEntity(Category category, Region region, User user) {
        return Store.builder()
                .storeName(this.storeName)
                .category(category)
                .region(region)
                .user(user)
                .build();
    }
}
