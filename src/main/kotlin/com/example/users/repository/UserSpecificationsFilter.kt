package com.example.users.repository

import com.example.users.dto.FilterOperation
import com.example.users.model.User
import org.springframework.data.jpa.domain.Specification

class UserSpecificationsFilter {
    companion object {

        fun withAttribute(fieldName: String, filterOperation: FilterOperation ,value: String): Specification<User>? {
            val values = value.split(",").map { it.trim() }
            return when (filterOperation) {
                FilterOperation.EQUALS -> {
                    if (values.size > 1) attributeIn(fieldName, values)
                    else attributeEquals(fieldName, value)
                }
                FilterOperation.GREATER_THAN -> attributeGreaterThan(fieldName, value)
                FilterOperation.LESS_THAN -> attributeLessThan(fieldName, value)
                FilterOperation.CONTAINS -> {
                    if (values.size > 1) attributeIn(fieldName, values)
                    else attributeLike(fieldName, value)

                }
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