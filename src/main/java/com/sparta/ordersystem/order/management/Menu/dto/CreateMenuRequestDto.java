package com.sparta.ordersystem.order.management.menu.dto;

import com.sparta.ordersystem.order.management.menu.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequestDto {

    UUID store_id;
    String menu_name;
    String content;
    Integer cost;

    public static Menu toEntity(CreateMenuRequestDto requestDto){
        Menu menu = Menu.builder()
                .menu_name(requestDto.getMenu_name())
                .cost(requestDto.getCost())
                .content(requestDto.getContent())
                .isActive(true)
                .build();
        return menu;
    }
}
