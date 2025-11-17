package yandex.practicum.dto

import java.time.OffsetDateTime

data class UserRequestDto(
    val user_id:Int,
    val username: String,
    val action: String,
    val timestamp: OffsetDateTime
)