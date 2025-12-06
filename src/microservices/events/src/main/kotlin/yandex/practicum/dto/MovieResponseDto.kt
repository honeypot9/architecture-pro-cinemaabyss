package yandex.practicum.dto

import org.springframework.http.HttpStatus

data class MovieResponseDto(
    val movieId: Int,
    val status: String
)
