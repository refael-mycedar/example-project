package com.example.users.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumValidatorImpl::class])
annotation class EnumValidator(
    val message: String = "Invalid value. This is not permitted.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = [],
    val enumClass: KClass<*>,
    val ignoreCase: Boolean = false
)