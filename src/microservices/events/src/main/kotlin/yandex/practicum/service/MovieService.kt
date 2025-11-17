package yandex.practicum.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import yandex.practicum.dto.MovieRequestDto
import yandex.practicum.dto.MovieResponseDto
import java.util.*

@Service
class MovieService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${app.kafka.topics.movie-events}")
    private lateinit var movieEventsTopic: String

    fun createMovie(createMovieDto: MovieRequestDto): MovieResponseDto {
        val future = kafkaTemplate.send(
            movieEventsTopic, UUID.randomUUID().toString(), createMovieDto
        )
        return MovieResponseDto(movieId = createMovieDto.movie_id, status = "success")
    }

}