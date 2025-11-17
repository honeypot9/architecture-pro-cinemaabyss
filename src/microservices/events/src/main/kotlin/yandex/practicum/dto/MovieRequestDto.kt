package yandex.practicum.dto

data class MovieRequestDto(
    val movie_id: Int,
    val title: String,
    val action: String,
    val user_id: Int
)
