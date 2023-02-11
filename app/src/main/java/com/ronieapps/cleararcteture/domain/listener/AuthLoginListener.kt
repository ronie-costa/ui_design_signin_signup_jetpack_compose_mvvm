package com.ronieapps.cleararcteture.domain.listener

import com.ronieapps.cleararcteture.domain.model.UserModel

interface AuthLoginListener {
    fun onSuccess(user: UserModel)
    fun onFailed(message: String)
}