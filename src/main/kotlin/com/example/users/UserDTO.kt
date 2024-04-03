package com.example.users

import jakarta.validation.constraints.*

class UserDTO(

    @field:NotNull(message = "First name is mandatory")
    @field:NotBlank(message = "First name is mandatory")
    @field:Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    val firstName: String? = null,

    @field:NotNull(message = "Last name is mandatory")
    @field:NotBlank(message = "Last name is mandatory")
    @field:Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    val lastName: String? = null,

    @field:NotNull(message = "Email is mandatory")
    @field:NotBlank(message = "Email is mandatory")
    @field:Email(message = "Email should be valid")
    @field:Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    val email: String? = null,

    @field:Digits(integer = 10, fraction = 0, message = "Balance must be a number")
    @field:Min(value = 0, message = "Balance must be greater than or equal to 0")
    val balance: Long? = 0
)