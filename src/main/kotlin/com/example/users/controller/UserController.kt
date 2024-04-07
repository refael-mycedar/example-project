package com.example.users.controller

import com.example.users.dto.CreateUserDTO
import com.example.users.dto.FilterDTO
import com.example.users.dto.ResponseUserDTO
import com.example.users.dto.UpdateUserDTO
import com.example.users.service.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@Valid @RequestBody user: CreateUserDTO): ResponseUserDTO {
        return userService.createUser(user)
    }

    @PostMapping("/list")
    fun findUsers(@Valid @RequestBody filterDTO: List<FilterDTO>?, pageable: Pageable): Page<ResponseUserDTO> {
        return userService.findUsers(filterDTO, pageable)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseUserDTO = userService.findUserById(userId)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") userId: Long, @Valid @RequestBody updateUserDTO: UpdateUserDTO): ResponseEntity<ResponseUserDTO> =
        userService.updateUser(userId, updateUserDTO).let { updatedUser ->
            ResponseEntity.ok(updatedUser)
        }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> =
        userService.deleteUser(userId).let {
            ResponseEntity<Void>(HttpStatus.OK)
        }
}
