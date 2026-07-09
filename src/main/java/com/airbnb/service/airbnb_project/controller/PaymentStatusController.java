package com.airbnb.service.airbnb_project.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Hidden
@RestController
public class PaymentStatusController {

    @GetMapping("/payment-success")
    public ResponseEntity<String> paymentSuccess() {
        return ResponseEntity.ok(
                Map.of(
                        "status", "SUCCESS",
                        "message", "Payment received. Your booking is being confirmed."
                ).toString()
        );
    }

    @GetMapping("/payment-cancel")
    public ResponseEntity<String> paymentCancelled() {
        return ResponseEntity.ok(Map.of(
                "status", "CANCELLED",
                "message", "Payment was cancelled by the user."
        ).toString());
    }
}