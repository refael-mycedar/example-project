package com.example.users.dto
import com.example.users.model.User


class ResponseUserDTO(user: User) {
    val id: Long = user.id
    val firstName: String? = user.firstName
    val lastName: String? = user.lastName
    val email: String? = user.email
    val balance: Long? = user.balance
}