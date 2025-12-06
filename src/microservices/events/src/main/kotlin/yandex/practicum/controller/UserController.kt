package yandex.practicum.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yandex.practicum.dto.UserRequestDto
import yandex.practicum.dto.UserResponseDto
import yandex.practicum.service.UserService

@RestController
@RequestMapping
@Tag(name = "User", description = "API для управления пользователями")
class UserController(private val userService: UserService) {

    @PostMapping("/api/events/user")
    @Operation(summary = "Добавить платеж")
    fun createUser(@RequestBody user: UserRequestDto): ResponseEntity<UserResponseDto> {
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

}