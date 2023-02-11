package com.ronieapps.cleararcteture.data.data_source.database

import com.google.firebase.auth.FirebaseAuth
import com.ronieapps.cleararcteture.domain.listener.AuthLoginListener
import com.ronieapps.cleararcteture.domain.listener.AuthSignupListener
import com.ronieapps.cleararcteture.domain.model.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthFirebaseDatabase @Inject constructor() {

    private val auth = FirebaseAuth.getInstance()

    val flowGetUser: Flow<UserModel> = flow {
        val authResult = auth.currentUser!!
        val user = UserModel(email = authResult.email!!)
        this.emit(user)
    }

    fun loginFirebaseAuth(user: UserModel, authLoginListener: AuthLoginListener) {
        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val userModel = UserModel( email = result.user?.email!! )
                authLoginListener.onSuccess(userModel)
            }.addOnFailureListener {
                authLoginListener.onFailed("Erro ao efetuar login")
            }
    }

    fun signUpFirebaseAuth(user: UserModel, authSignupListener: AuthSignupListener) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { result ->
                val userModel = UserModel( email = result.user?.email!! )
                authSignupListener.signupSuccess(userModel)
            }.addOnFailureListener {
                authSignupListener.signupFailure("e-mail ou senha invalido, texte novamente!")
            }
    }
}