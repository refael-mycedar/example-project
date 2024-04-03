package com.example.users

import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<out Any> {
        val cause = ex.cause
        if (cause is ConstraintViolationException) {
            if (cause.sqlException.message?.contains("uc_user_email") == true) {
                val errorResponse = mapOf("email" to "Email already exists.")
                return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
            }
        }
        // If not handled above, return a generic error response.
        return ResponseEntity("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): Map<String, String?> {
        val errors = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage
        }
        return errors
    }
}
