package com.example.users.validation

import com.example.users.dto.FilterOperation
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FilterOperationValidator : ConstraintValidator<ValidFilterOperation, FilterOperation> {

    private val allowedOperations = setOf(FilterOperation.EQUALS, FilterOperation.CONTAINS) // Example

    override fun initialize(constraintAnnotation: ValidFilterOperation) {
        println(); // Placeholder
    }

    override fun isValid(value: FilterOperation?, context: ConstraintValidatorContext?): Boolean {
        return value in allowedOperations
    }
}
