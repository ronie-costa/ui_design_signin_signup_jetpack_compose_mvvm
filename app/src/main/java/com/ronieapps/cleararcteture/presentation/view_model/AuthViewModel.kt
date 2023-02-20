package com.ronieapps.cleararcteture.presentation.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ronieapps.cleararcteture.core.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieapps.cleararcteture.core.domain.model.UserModel
import com.ronieapps.cleararcteture.core.sealed.AuthState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val authState: MutableState<AuthState> = mutableStateOf(AuthState.Initial)

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()


    private fun setMessage(message: String) {
        viewModelScope.launch {
            _message.emit(message)
        }
    }

    fun startLogin(user: UserModel) {
        when {
            user.email.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar login")
            }
            user.password.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar login")
            }
            user.password.length <= 5 -> {
                setMessage("Digite no minímo 6 caracteres para efetuar login")
            }
            else -> {
                val isSuccess: () -> Unit = {
                    authState.value = AuthState.Success
                }
                val isFailure: (String) -> Unit = {
                    authState.value = AuthState.Failure
                    setMessage(it)
                }
                repository.startLoginRepo(user, isSuccess, isFailure)
            }
        }

    }

    fun startSignUp(user: UserModel) {
        when {
            user.name.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar o casdastro")
            }
            user.email.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar o casdastro")
            }
            user.password.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar o casdastro")
            }
            user.confirmPassword.isEmpty() -> {
                setMessage("Preencha todos os campos para efetuar o casdastro")
            }
            user.password.length <= 5 -> {
                setMessage("Digite uma senha com mais de 5 carecteres.")
            }
            user.password != user.confirmPassword -> {
                setMessage("Campo Senha precisa ser igua Confirmar Senha")
            }
            else -> {
                val isSuccess: () -> Unit = {
                    authState.value = AuthState.Success
                }
                val isFailure: (String) -> Unit = {
                    authState.value = AuthState.Failure
                    setMessage(it)
                }
                repository.startSignupRepo(user, isSuccess, isFailure)
            }
        }
    }

    fun logOut() {
        val isLogOut: (Boolean) -> Unit = {
            if (it) {
                authState.value = AuthState.Failure
            } else {
                authState.value = AuthState.Success
            }
        }
        repository.logOut(isLogOut)
    }

}