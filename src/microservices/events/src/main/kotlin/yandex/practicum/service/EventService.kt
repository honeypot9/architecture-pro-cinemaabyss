package yandex.practicum.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import yandex.practicum.dto.MovieRequestDto
import yandex.practicum.dto.PaymentDto
import yandex.practicum.dto.PaymentRequestDto
import yandex.practicum.dto.UserRequestDto


@Service
class EventService {

    private val logger = LoggerFactory.getLogger(EventService::class.java)

    @KafkaListener(
        topics = ["\${app.kafka.topics.movie-events}"],
        groupId = "\${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consumeMovieEvent(
        @Payload movieEvent: MovieRequestDto,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String?,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long
    ) {
        logger.info("Received movie event - Key: $key, Partition: $partition, Timestamp: $timestamp")
        logger.info("movie Event: $movieEvent")
    }

    @KafkaListener(
        topics = ["\${app.kafka.topics.payment-events}"],
        groupId = "\${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consumePaymentEvent(
        @Payload paymentEvent: PaymentRequestDto,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String?,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long
    ) {
        logger.info("Received payment event - Key: $key, Partition: $partition, Timestamp: $timestamp")
        logger.info("payment Event: $paymentEvent")
    }

    @KafkaListener(
        topics = ["\${app.kafka.topics.user-events}"],
        groupId = "\${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consumeUserEvent(
        @Payload userEvent: UserRequestDto,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String?,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long
    ) {
        logger.info("Received user event - Key: $key, Partition: $partition, Timestamp: $timestamp")
        logger.info("user Event: $userEvent")
    }
}