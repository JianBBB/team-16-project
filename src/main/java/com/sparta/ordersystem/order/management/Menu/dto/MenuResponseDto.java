package com.sparta.ordersystem.order.management.menu.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class MenuResponseDto {
    UUID menu_id;
    UUID store_id;
    String menu_name;
    String content;
    Integer cost;
    Boolean is_active;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
