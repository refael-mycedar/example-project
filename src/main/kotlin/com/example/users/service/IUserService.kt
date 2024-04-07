package com.example.users.service

import com.example.users.dto.CreateUserDTO
import com.example.users.dto.FilterDTO
import com.example.users.dto.ResponseUserDTO
import com.example.users.dto.UpdateUserDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IUserService {
    fun createUser(createUserDTO: CreateUserDTO): ResponseUserDTO
    fun findUsers(filterDTO: List<FilterDTO>?, pageable: Pageable): Page<ResponseUserDTO>
    fun findUserById(id: Long): ResponseUserDTO
    fun updateUser(id: Long, userUpdateDTO: UpdateUserDTO): ResponseUserDTO
    fun deleteUser(id: Long)
}
