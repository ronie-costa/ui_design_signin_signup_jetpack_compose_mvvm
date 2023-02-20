package com.ronieapps.cleararcteture.data.data_source.database


import com.google.firebase.auth.FirebaseAuth
import com.ronieapps.cleararcteture.core.domain.model.UserModel
import javax.inject.Inject

class AuthFirebaseDatabase @Inject constructor() {
    private val auth = FirebaseAuth.getInstance()

    fun loginFirebaseAuth(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit
    ) {
        val result = auth.signInWithEmailAndPassword(user.email, user.password)
        result.addOnSuccessListener {
            isSuccess()
        }
        result.addOnFailureListener {
            val message = "Erro ao efetuar login"
            isFailure(message)
        }
    }

    fun signUpFirebaseAuth(
        user: UserModel,
        isSuccess: () -> Unit,
        isFailure: (String) -> Unit
    ) {
        val result = auth.createUserWithEmailAndPassword(user.email, user.password)
        result.addOnSuccessListener {
            isSuccess()
        }
        result.addOnFailureListener {
            val message = "e-mail ou senha invalido, tente novamente!"
            isFailure(message)
        }
    }

    fun logOut(isLogOut: (Boolean) -> Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            auth.signOut()
            isLogOut(true)
        } else {
            isLogOut(false)
        }
    }

}