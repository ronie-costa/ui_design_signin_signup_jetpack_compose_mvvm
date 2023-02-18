package com.ronieapps.cleararcteture.core.utils

sealed class AuthResource<T>(val user: T? = null, val message: String? = null) {
    class Success<T>(user: T?) : AuthResource<T>(user)
    class Failure<T>(message: String, user: T?) : AuthResource<T>(user, message)
}
