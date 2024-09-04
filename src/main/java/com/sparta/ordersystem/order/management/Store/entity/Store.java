package com.sparta.ordersystem.order.management.store.entity;

import com.sparta.ordersystem.order.management.category.entity.Category;
import com.sparta.ordersystem.order.management.region.entity.Region;
import com.sparta.ordersystem.order.management.user.entity.User;
import com.sparta.ordersystem.order.management.common.Timestamped;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="p_store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store extends Timestamped {

    @Id
    @Column(name="store_id")
    private UUID storeId;

    @Column(name="store_name", nullable = false)
    private String storeName;

    @Column(name="is_active")
    @ColumnDefault("true")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Builder
    public Store(String storeName, Category category, Region region, User user) {
        this.storeId = UUID.randomUUID();
        this.storeName = storeName;
        this.category = category;
        this.region = region;
        this.user = user;
        this.isActive = true;
    }

    public void updateStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void updateCategory(Category category) {
        this.category = category;
    }

    public void updateRegion(Region region) {
        this.region = region;
    }

    public void softDeleted(Long userId){
        this.isActive = false;
        this.deleted_at = LocalDateTime.now();
        this.deleted_by = userId;
    }
}