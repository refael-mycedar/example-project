package com.example.users.exception

class EmailAlreadyExistsException(email: String) :
    RuntimeException("Email already exists: $email")
