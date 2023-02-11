package com.ronieapps.cleararcteture.domain.listener

import com.ronieapps.cleararcteture.domain.model.UserModel

interface AuthSignupListener {
    fun signupSuccess(user: UserModel)
    fun signupFailure(message: String)
}