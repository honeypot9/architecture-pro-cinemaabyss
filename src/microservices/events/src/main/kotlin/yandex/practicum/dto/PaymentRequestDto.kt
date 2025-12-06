package yandex.practicum.dto

import java.time.OffsetDateTime

data class PaymentRequestDto(
    val payment_id: Int,
    val user_id: Int,
    val amount: Double,
    val status: String,
    val timestamp: OffsetDateTime,
    val method_type: String
)