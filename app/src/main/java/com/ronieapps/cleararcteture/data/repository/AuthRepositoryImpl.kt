package com.ronieapps.cleararcteture.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FederatedAuthProvider
import com.ronieapps.cleararcteture.data.data_source.database.AuthFirebaseDatabase
import com.ronieapps.cleararcteture.core.domain.model.UserModel
import com.ronieapps.cleararcteture.core.domain.repository.AuthRepository
import com.ronieapps.cleararcteture.core.utils.AuthResource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AuthRepositoryImpl @Inject constructor(
    private val authDatabase: AuthFirebaseDatabase
) : AuthRepository {

    override fun startLoginRepo(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit
    ) = authDatabase.loginFirebaseAuth(user, isSuccess, isFailure)

    override fun startSignupRepo(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit
    ) = authDatabase.signUpFirebaseAuth(user, isSuccess, isFailure)


    override fun logOut(isLogOut: (Boolean) -> Unit) {
        authDatabase.logOut(isLogOut)
    }

}