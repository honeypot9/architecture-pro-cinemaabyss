package yandex.practicum.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yandex.practicum.dto.PaymentRequestDto
import yandex.practicum.dto.PaymentResponseDto
import yandex.practicum.service.PaymentService

@RestController
@RequestMapping
@Tag(name = "Payments", description = "API для управления платежами")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/api/events/payment")
    @Operation(summary = "Добавить платеж")
    fun createPayment(@RequestBody payment: PaymentRequestDto): ResponseEntity<PaymentResponseDto> {
        return ResponseEntity(paymentService.createPayment(payment),HttpStatus.CREATED)
    }

}