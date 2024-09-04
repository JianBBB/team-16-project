package com.sparta.ordersystem.order.management.delivery.service;

import com.sparta.ordersystem.order.management.delivery.dto.CreateDeliveryRequestDto;
import com.sparta.ordersystem.order.management.delivery.dto.DeliveryResponseDto;
import com.sparta.ordersystem.order.management.delivery.dto.UpdateDeliveryRequestDto;
import com.sparta.ordersystem.order.management.delivery.entity.Delivery;
import com.sparta.ordersystem.order.management.delivery.exception.DeliveryNotFoundException;
import com.sparta.ordersystem.order.management.delivery.repository.DeliveryRepository;
import com.sparta.ordersystem.order.management.order.entity.Order;
import com.sparta.ordersystem.order.management.order.exception.OrderNotFoundException;
import com.sparta.ordersystem.order.management.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {


    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void createDelivery(CreateDeliveryRequestDto requestDto) {

        //존재하는 주문ID인지 검증
       Order order = orderRepository.findByOrderIdAndIsActiveTrue(requestDto.getOrder_id()).orElseThrow(
                () -> new OrderNotFoundException("존재하지 않는 주문 ID입니다.")
        );

        Delivery delivery = requestDto.toEntity(true,order);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public DeliveryResponseDto updateDelivery(UUID deliveryId, UpdateDeliveryRequestDto updateDeliveryRequestDto) {

        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsActiveTrue(deliveryId).orElseThrow(
                () -> new DeliveryNotFoundException("존재하지 않는 배달 ID입니다.")
        );

        delivery.updateDelivery(updateDeliveryRequestDto);

        Delivery newDelivery = deliveryRepository.save(delivery);

        return convertDeliveryToDeliveryResponseDto(newDelivery);

    }

    @Transactional
    public DeliveryResponseDto deleteDelivery(UUID deliveryId,Long user_id) {

        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsActiveTrue(deliveryId).orElseThrow(
                () -> new DeliveryNotFoundException("존재하지 않는 배달 ID입니다.")
        );

        delivery.deleteDelivery(user_id);

        Delivery newDelivery = deliveryRepository.save(delivery);

        return convertDeliveryToDeliveryResponseDto(newDelivery);
    }

    /***
     * 고객이 주문한 내역의 배송지를 조회하는 함수
     * @param orderId
     * @return
     */
    @Transactional(readOnly = true)
    public DeliveryResponseDto getDeliveryByOrderId(UUID orderId) {
        //주문 ID 존재하는 지 검증
        Order order = orderRepository.findByOrderIdAndIsActiveTrue(orderId).orElseThrow(
                () -> new OrderNotFoundException("존재하지 않는 주문 ID입니다.")
        );

        Delivery delivery = deliveryRepository.findByOrderAndIsActiveTrue(order).orElseThrow(
                () -> new DeliveryNotFoundException("존재하지 않는 배달 ID입니다.")
        );

        return convertDeliveryToDeliveryResponseDto(delivery);
    }

    //Entity -> Dto
    private DeliveryResponseDto convertDeliveryToDeliveryResponseDto(Delivery delivery) {
        return DeliveryResponseDto.builder()
                .delivery_id(delivery.getDeliveryId())
                .address(delivery.getAddress())
                .requset_note(delivery.getRequest_note())
                .order_id(delivery.getOrder().getOrderId())
                .created_at(delivery.getCreated_at())
                .updated_at(delivery.getUpdated_at())
                .isActive(delivery.getIsActive())
                .build();
    }
}
