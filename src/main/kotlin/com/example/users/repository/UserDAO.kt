package com.example.users.repository

import com.example.users.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserDAO  : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun existsByEmail(email: String): Boolean
}
