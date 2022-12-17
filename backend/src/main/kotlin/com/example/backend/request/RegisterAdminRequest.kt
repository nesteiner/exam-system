package com.example.backend.request

import com.example.backend.model.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

class RegisterAdminRequest(
    @NotBlank(message = "name cannot be blank")
    @Length(min = 5, max = 20, message = "name must between 5 - 20")
    val name: String,

    @NotBlank(message = "sex cannot be blank")
    @Length(min = 2, max = 2, message = "sex must be 男 or 女")
    val sex: String,

    @NotBlank(message = "telephone cannot be blank")
    @Length(min = 11, max = 11, message = "length of telephone must be 11")
    val telephone: String,

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email pattern error")
    val email: String,

    @NotBlank(message = "password cannot be blank")
    val passwordHash: String,

    @NotBlank(message = "cardId cannot be blank")
    @Length(max = 18, message = "length of cardId must less than 18")
    val cardId: String,
)