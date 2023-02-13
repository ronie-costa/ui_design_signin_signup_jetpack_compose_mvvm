package com.ronieapps.cleararcteture.domain.repository

import com.ronieapps.cleararcteture.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val flowUserRepo: Flow<UserModel>

    fun startLoginRepo(
        user: UserModel,
        isSuccess: (user: UserModel) -> Unit,
        isFailure: (message: String) -> Unit
    )

    fun startSignupRepo(
        user: UserModel,
        isSuccess: (user: UserModel) -> Unit,
        isFailure: (message: String) -> Unit
    )
}