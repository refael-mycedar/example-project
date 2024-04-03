package com.example.users

import org.springframework.data.jpa.domain.Specification

class UserSpecificationsFilter {
    companion object {

        fun withAttribute(attribute: String, value: String?): Specification<User>? {
            if (value == null) return null

            val startsWithPercent = value.startsWith("*")
            val endsWithPercent = value.endsWith("*")
            val isGreaterThan = value.startsWith(">")
            val isLessThan = value.startsWith("<")

            return when {
                value.contains(",") -> {
                    val values = value.split(',').map { it.trim() }
                    attributeIn(attribute, values)
                }
                isGreaterThan -> {
                    val numericValue = value.substring(1) // Remove the leading '<'
                    attributeGreaterThan(attribute, numericValue)
                }
                isLessThan -> {
                    val numericValue = value.substring(1) // Remove the leading '>'
                    attributeLessThan(attribute, numericValue)
                }
                startsWithPercent || endsWithPercent -> {
                    val likeValue = value.trim('*') // Remove '%' from start/end for LIKE query
                    attributeLike(attribute, likeValue)
                }
                else -> attributeEquals(attribute, value)
            }
        }

        private fun attributeGreaterThan(attribute: String, value: String?): Specification<User>? =
            if (value == null) null
            else Specification { root, _, criteriaBuilder ->
                criteriaBuilder.greaterThan(root.get(attribute), value)
            }

        private fun attributeLessThan(attribute: String, value: String?): Specification<User>? =
            if (value == null) null
            else Specification { root, _, criteriaBuilder ->
                criteriaBuilder.lessThan(root.get(attribute), value)
            }

        private fun attributeEquals(attribute: String, value: Any?): Specification<User>? =
            if (value == null) null
            else Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Any>(attribute), value)
            }

        private fun attributeIn(attribute: String, values: List<Any>?): Specification<User>? =
            if (values == null) null
            else Specification { root, _, criteriaBuilder ->
                root.get<Any>(attribute).`in`(values)
            }

        private fun attributeLike(attribute: String, value: String?): Specification<User>? =
            if (value == null) null
            else Specification { root, _, criteriaBuilder ->
                criteriaBuilder.like(root.get(attribute), "%$value%")
            }
    }
}