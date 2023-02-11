package com.ronieapps.cleararcteture.domain.model

data class UserModel(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)
