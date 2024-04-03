package com.example.users

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDAO: UserDAO
) {

    fun createUser(userDTO: UserDTO): User {
        // Convert UserDTO to User entity
        val user = User(
            firstName = userDTO.firstName,
            lastName = userDTO.lastName,
            email = userDTO.email,
            balance = userDTO.balance
        )
        // Save the User entity to the database
        return userDAO.save(user)
    }

    fun findUsers(filters: Map<String, Any>, pageable: Pageable): Page<User> {
        val specs = filters.map { UserSpecificationsFilter.withAttribute(it.key, it.value.toString()) }
            .filterNotNull()
            .reduceOrNull { spec1, spec2 -> spec1.and(spec2) }

        return if(specs != null) userDAO.findAll(specs, pageable)
        else userDAO.findAll(pageable)
    }

    fun findUserById(id: Long): User = userDAO.findById(id)
        .orElseThrow { Exception("User not found with id: $id") }

    fun updateUser(id: Long, user: User): User =
        userDAO.findById(id).map { existingUser ->
            val updatedUser: User = existingUser.copy(
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                balance = user.balance
            )
            userDAO.save(updatedUser)
        }.orElseThrow { Exception("User not found with id: $id") }

    fun deleteUser(id: Long) = userDAO.deleteById(id)
}