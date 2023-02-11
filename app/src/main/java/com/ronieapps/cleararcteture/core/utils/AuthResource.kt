package com.ronieapps.cleararcteture.core.utils

import com.ronieapps.cleararcteture.domain.model.UserModel

sealed class AuthResource<T>(val user: T? = null, val message: String? = null) {
    class Success<T>(user: T?) : AuthResource<T>(user)
    class Failure<T>(
        user: T? = null,
        message: String
    ) : AuthResource<T>(user, message)
}
