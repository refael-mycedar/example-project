package com.example.users.service

import com.example.users.dto.CreateUserDTO
import com.example.users.dto.FilterDTO
import com.example.users.dto.ResponseUserDTO
import com.example.users.dto.UpdateUserDTO
import com.example.users.exception.EmailAlreadyExistsException
import com.example.users.model.User
import com.example.users.repository.UserDAO
import com.example.users.repository.UserSpecificationsFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userDAO: UserDAO
): IUserService {

    override fun createUser(createUserDTO: CreateUserDTO): ResponseUserDTO {
        // Validate if the email is unique
        validateUniqueEmail(createUserDTO.email)

        // Convert CreateUserDTO to User entity
        val user = User(
            firstName = createUserDTO.firstName,
            lastName = createUserDTO.lastName,
            email = createUserDTO.email,
            balance = createUserDTO.balance
        )
        // Save the User entity to the database
        val newUser = userDAO.save(user)
        // Convert the saved User entity to ResponseUserDTO
        return ResponseUserDTO(newUser)
    }

    override fun findUsers(filterDTO: List<FilterDTO>?, pageable: Pageable): Page<ResponseUserDTO> {

        val filter = filterDTO ?: emptyList()

        val specs = filter.map { UserSpecificationsFilter.withAttribute(it.fieldName, it.operation, it.value) }
            .filterNotNull()
            .reduceOrNull { spec1, spec2 -> spec1.and(spec2) }

        val users = if(specs != null) userDAO.findAll(specs, pageable)
        else userDAO.findAll(pageable)

        return users.map { user -> ResponseUserDTO(user) }
    }

    override fun findUserById(id: Long): ResponseUserDTO =
        userDAO.findById(id).map { user -> ResponseUserDTO(user) }
            .orElseThrow { Exception("User not found with id: $id") }

    override fun updateUser(id: Long, userUpdateDTO: UpdateUserDTO): ResponseUserDTO =
           userDAO.findById(id).map { existingUser ->
            val updatedUser = existingUser.copy(
                firstName = userUpdateDTO.firstName ?: existingUser.firstName,
                lastName = userUpdateDTO.lastName ?: existingUser.lastName,
                balance = userUpdateDTO.balance ?: existingUser.balance
            )
            ResponseUserDTO(updatedUser)
        }.orElseThrow { Exception("User not found with id: $id") }
    override fun deleteUser(id: Long) = userDAO.deleteById(id)

    private fun validateUniqueEmail(email: String?) {
        email?.let {
            if (userDAO.existsByEmail(it)) {
                throw EmailAlreadyExistsException(it)
            }
        }
    }
}