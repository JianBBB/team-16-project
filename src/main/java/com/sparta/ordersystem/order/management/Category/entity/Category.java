package com.sparta.ordersystem.order.management.category.entity;


import com.sparta.ordersystem.order.management.store.entity.Store;
import com.sparta.ordersystem.order.management.common.Timestamped;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="p_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends Timestamped {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID categoryId;

    @Column(name="category_name")
    private String categoryName;

    @ColumnDefault("true")
    @Column(name="is_active")
    boolean isActive;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Store> stores = new ArrayList<>();


    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.isActive = true;
    }

    public void updateCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void softDeleted(Long userId){
        this.isActive = false;
        this.deleted_at = LocalDateTime.now();
        this.deleted_by = userId;

        if(!stores.isEmpty()) {
            for (Store store : stores) {
                store.softDeleted(userId);
            }
        }
    }
}
