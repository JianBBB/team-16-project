package com.sparta.ordersystem.order.management.order.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 ResponseDto의 공통적으로 날짜와 사람의 값을 포함시키기 위한 추상클래스
 **/
@Getter
@SuperBuilder
public abstract class BaseTimeStamped {
    private LocalDateTime created_at;

    private Long created_by;

    private LocalDateTime updated_at;

    private Long updated_by;

    private LocalDateTime deleted_at;

    private Long deleted_by;
}
