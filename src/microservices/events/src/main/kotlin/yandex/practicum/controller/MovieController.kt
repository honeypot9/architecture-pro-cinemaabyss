package yandex.practicum.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yandex.practicum.dto.MovieRequestDto
import yandex.practicum.dto.MovieResponseDto
import yandex.practicum.service.MovieService

@RestController
@Tag(name = "Movie", description = "API для управления фильмами")
@RequestMapping
class MovieController(private val movieService: MovieService) {

    @PostMapping("/api/events/movie")
    @Operation(summary = "Добавить фильм")
    fun createMovie(@RequestBody movie: MovieRequestDto): ResponseEntity<MovieResponseDto> {
        return ResponseEntity(movieService.createMovie(movie),HttpStatus.CREATED)
    }

}