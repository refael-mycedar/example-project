package com.example.users.validation

import com.example.users.repository.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueEmailValidator : ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private lateinit var userRepository: UserDAO


    override fun isValid(email: String?, context: ConstraintValidatorContext?): Boolean {
        if (email == null) {
            // Consider null as valid or not based on your requirements
            return true
        }
        return !userRepository.existsByEmail(email)
    }
}
