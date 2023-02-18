package com.ronieapps.cleararcteture.core.domain.repository

import com.google.firebase.auth.AuthResult
import com.ronieapps.cleararcteture.core.domain.model.UserModel
import com.ronieapps.cleararcteture.core.utils.AuthResource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun startLoginRepo(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit

    )

    fun startSignupRepo(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit
    )

    fun logOut(isLogOut: (Boolean) -> Unit)
}