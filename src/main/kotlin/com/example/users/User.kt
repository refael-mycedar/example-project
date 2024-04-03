package com.example.users

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "first_name", nullable = false, length = 50)
    val firstName: String?,

    @Column(name = "last_name", nullable = false, length = 50)
    val lastName: String?,

    @Column(nullable = false, unique = true)
    val email: String?,

    @Column(nullable = true)
    val balance: Long?,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: Date? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Date? = null,
)