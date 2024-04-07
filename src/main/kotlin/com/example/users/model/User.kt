package com.example.users.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import java.util.regex.Pattern

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "first_name", nullable = false, length = 50)
    var firstName: String?,

    @Column(name = "last_name", nullable = false, length = 50)
    val lastName: String?,

    @Column(nullable = false)
    var email: String?,

    @Column(nullable = true)
    val balance: Long?,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: Date? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Date? = null,
) {
    @PreUpdate
    fun validateUniqueEmail() {
        val regex = "^[A-Za-z0-9+_.-]+@(.+)$"
        val pattern = Pattern.compile(regex)

        if (email == null || !pattern.matcher(email!!).matches()) {
            throw IllegalArgumentException("Email is not valid")
        }
    }
}