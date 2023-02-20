package com.ronieapps.cleararcteture.core.sealed

sealed class AuthState {
    object Initial: AuthState()
    object Success: AuthState()
    object Failure: AuthState()
}
