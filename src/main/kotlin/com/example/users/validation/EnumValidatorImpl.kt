package com.example.users.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EnumValidatorImpl(private var annotation: EnumValidator) : ConstraintValidator<EnumValidator, CharSequence> {

    private lateinit var values: Array<String>

    override fun initialize(constraintAnnotation: EnumValidator) {
        values = constraintAnnotation.enumClass.java.enumConstants
            .map { it.toString() }.toTypedArray()
    }

    override fun isValid(charSequence: CharSequence?, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        if (charSequence == null) {
            return true
        }

        return if(annotation.ignoreCase) {
            values.any { it.equals(charSequence.toString(), ignoreCase = true) }
        } else {
            values.contains(charSequence.toString())
        }
    }
}