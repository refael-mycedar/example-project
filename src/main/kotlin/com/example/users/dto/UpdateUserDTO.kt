package com.example.users.dto

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

class UpdateUserDTO(
    @field:Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    val firstName: String? = null,

    @field:Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    val lastName: String? = null,

    @field:Digits(integer = 10, fraction = 0, message = "Balance must be a number")
    @field:Min(value = 0, message = "Balance must be greater than or equal to 0")
    val balance: Long? = 0
)
