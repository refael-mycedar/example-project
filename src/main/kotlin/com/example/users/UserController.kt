package com.example.users

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
    fun createUser(@Valid @RequestBody user: UserDTO): User {
        return userService.createUser(user)
    }

    @GetMapping
    fun findUsersWithGlobalFilter(@RequestParam filters: Map<String, String>, pageable: Pageable): Page<User> {
        //  filter keys must match UserDTO attributes
        val userAttributeAttr = UserDTO::class.java.declaredFields.map { it.name }
        // Convert filter values to appropriate types if necessary
        val convertedFilters = filters.filter { userAttributeAttr.contains(it.key) }
        return userService.findUsers(convertedFilters, pageable)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): User = userService.findUserById(userId)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") userId: Long, @RequestBody newUser: User): ResponseEntity<User> =
        userService.updateUser(userId, newUser).let { updatedUser ->
            ResponseEntity.ok(updatedUser)
        }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> =
        userService.deleteUser(userId).let {
            ResponseEntity<Void>(HttpStatus.OK)
        }
}
