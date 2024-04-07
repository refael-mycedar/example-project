package com.example.users.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
annotation class UniqueEmail(
    val message: String = "Email is already in use",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
