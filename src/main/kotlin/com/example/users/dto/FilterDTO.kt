package com.example.users.dto


data class FilterDTO(
    val fieldName: String,
    val operation: FilterOperation,
    val value: String
)