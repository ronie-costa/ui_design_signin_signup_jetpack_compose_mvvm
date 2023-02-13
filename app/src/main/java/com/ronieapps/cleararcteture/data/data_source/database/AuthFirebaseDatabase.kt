package com.ronieapps.cleararcteture.data.data_source.database

import com.google.firebase.auth.FirebaseAuth
import com.ronieapps.cleararcteture.domain.model.UserModel
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

    fun loginFirebaseAuth(
        user: UserModel,
        isSuccess: (user: UserModel) -> Unit,
        isFailure: (message: String) -> Unit
    ) {
        val result = auth.signInWithEmailAndPassword(user.email, user.password)

        result.addOnSuccessListener { authResult ->
            val userModel = UserModel( email = authResult.user?.email!! )
            isSuccess(userModel)
        }
        result.addOnFailureListener {
            val message = "Erro ao efetuar login"
            isFailure(message)
        }
    }


    fun signUpFirebaseAuth(
        user: UserModel,
        isSuccess: (user: UserModel) -> Unit,
        isFailure: (message: String) -> Unit
    ) {
        val result = auth.createUserWithEmailAndPassword(user.email, user.password)

        result.addOnSuccessListener { authResult ->
            val userModel = UserModel( email = authResult.user?.email!! )
            isSuccess(userModel)
        }
        result.addOnFailureListener {
            val message = "e-mail ou senha invalido, texte novamente!"
            isFailure(message)
        }
    }

}