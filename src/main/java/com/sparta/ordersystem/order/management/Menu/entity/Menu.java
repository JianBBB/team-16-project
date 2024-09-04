package com.sparta.ordersystem.order.management.menu.entity;

import com.sparta.ordersystem.order.management.menu.dto.UpdateRequestDto;
import com.sparta.ordersystem.order.management.OrderMenu.OrderMenu;
import com.sparta.ordersystem.order.management.store.entity.Store;
import com.sparta.ordersystem.order.management.common.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_menu")
public class Menu extends Timestamped{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "menu_id")
    private UUID menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String menu_name;

    private Integer cost;

    private String content;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "menu")
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    public void deleteMenu(){
        this.isActive = false;
    }

    public void updateMenu(UpdateRequestDto updateRequestDto) {
        this.menu_name = updateRequestDto.getMenu_name();
        this.cost = updateRequestDto.getCost();
        this.content = updateRequestDto.getContent();

    }

    public void addStore(Store store) {
        this.store = store;
    }
}
