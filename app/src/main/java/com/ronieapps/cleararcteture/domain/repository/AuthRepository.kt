package com.ronieapps.cleararcteture.domain.repository

import com.ronieapps.cleararcteture.domain.listener.AuthLoginListener
import com.ronieapps.cleararcteture.domain.listener.AuthSignupListener
import com.ronieapps.cleararcteture.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val flowUserRepo: Flow<UserModel>

    fun startLoginRepo(
        user: UserModel,
        authLoginListener: AuthLoginListener
    )

    fun startSignupRepo(
        user: UserModel,
        authSignupListener: AuthSignupListener
    )
}