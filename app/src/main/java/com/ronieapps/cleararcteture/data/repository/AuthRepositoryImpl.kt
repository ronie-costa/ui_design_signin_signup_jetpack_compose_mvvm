package com.ronieapps.cleararcteture.data.repository

import com.ronieapps.cleararcteture.data.data_source.database.AuthFirebaseDatabase
import com.ronieapps.cleararcteture.domain.listener.AuthLoginListener
import com.ronieapps.cleararcteture.domain.listener.AuthSignupListener
import com.ronieapps.cleararcteture.domain.model.UserModel
import com.ronieapps.cleararcteture.domain.repository.AuthRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AuthRepositoryImpl @Inject constructor(
    private val authDatabase: AuthFirebaseDatabase
) : AuthRepository {

    override val flowUserRepo: Flow<UserModel>
        get() = authDatabase.flowGetUser

    override fun startLoginRepo(user: UserModel, authLoginListener: AuthLoginListener) {
        authDatabase.loginFirebaseAuth(user, authLoginListener)
    }

    override fun startSignupRepo(user: UserModel, authSignupListener: AuthSignupListener) {
        authDatabase.signUpFirebaseAuth(user, authSignupListener)
    }

}