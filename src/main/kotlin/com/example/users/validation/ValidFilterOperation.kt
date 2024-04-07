package com.example.users.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FilterOperationValidator::class])
annotation class ValidFilterOperation(
    val message: String = "Invalid operation",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
