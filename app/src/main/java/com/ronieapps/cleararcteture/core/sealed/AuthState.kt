package com.ronieapps.cleararcteture.core.sealed

sealed class AuthState {
    object Success: AuthState()
    object Failure: AuthState()
    object Initial: AuthState()
}
