package com.sparta.ordersystem.order.management.payment.controller;

import com.sparta.ordersystem.order.management.payment.dto.CreatePaymentRequestDto;
import com.sparta.ordersystem.order.management.payment.dto.UpdateStatusRequestDto;
import com.sparta.ordersystem.order.management.payment.service.PaymentService;
import com.sparta.ordersystem.order.management.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    /***
     * 결제 내역 저장
     * @param requestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody CreatePaymentRequestDto requestDto) {
        paymentService.cretePayment(requestDto);
        return ResponseEntity.ok().body("결제가 저장되었습니다.");
    }

    @GetMapping("{payment_id}")
    public ResponseEntity<?> getPaymentsInDetail(@PathVariable UUID payment_id) {

        return ResponseEntity.ok().body(paymentService.getPaymentsInDetail(payment_id));
    }

    @PatchMapping("{payment_id}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable UUID payment_id,
                                                 @RequestBody UpdateStatusRequestDto requestDto,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        paymentService.updatePaymentStatus(payment_id,requestDto,userDetails.getUser());
        return ResponseEntity.ok().body("결제 상태가 "+ requestDto.getPaymentStatus() + "로 수정되었습니다.");
    }

    @GetMapping
    public ResponseEntity<?> getAllPayments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(paymentService.getAllPaymentsByUserId(userDetails.getUser()));

    }
}
