package yandex.practicum.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import yandex.practicum.dto.UserRequestDto
import yandex.practicum.dto.UserResponseDto
import java.util.*

@Service
class UserService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {

    @Value("\${app.kafka.topics.user-events}")
    private lateinit var userEventsTopic: String

    fun createUser(createUser: UserRequestDto): UserResponseDto {
        val future = kafkaTemplate.send(
            userEventsTopic, UUID.randomUUID().toString(), createUser
        )
        return UserResponseDto(
            userId = createUser.user_id, status = "success"
        )
    }
}