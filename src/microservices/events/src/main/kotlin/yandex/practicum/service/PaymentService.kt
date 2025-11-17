package yandex.practicum.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import yandex.practicum.dto.PaymentDto
import yandex.practicum.dto.PaymentRequestDto
import yandex.practicum.dto.PaymentResponseDto
import java.time.OffsetDateTime
import java.util.*

@Service
class PaymentService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {

    @Value("\${app.kafka.topics.payment-events}")
    private lateinit var paymentEventsTopic: String

    fun createPayment(createPayment: PaymentRequestDto): PaymentResponseDto {
        val future = kafkaTemplate.send(
            paymentEventsTopic, UUID.randomUUID().toString(), createPayment
        )
        return PaymentResponseDto(paymentId = createPayment.payment_id, status = "success")
    }

}