package com.ronieapps.cleararcteture.presentation.view_model

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
    private val _authStateFlow = MutableStateFlow<AuthState>(AuthState.Initial)
    val authStateFlow: Flow<AuthState> = _authStateFlow

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
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Success)
                    }
                }
                val isFailure: (String) -> Unit = {
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Failure)
                        _message.emit(it)
                    }
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
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Success)
                    }
                }
                val isFailure: (String) -> Unit = {
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Failure)
                        setMessage(it)
                    }
                }
                repository.startSignupRepo(user, isSuccess, isFailure)
            }
        }
    }

    fun logOut() {
        val isLogOut: (Boolean) -> Unit = {
            when (it) {
                true -> {
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Initial)
                    }
                }
                false -> {
                    viewModelScope.launch {
                        _authStateFlow.emit(AuthState.Success)
                    }
                }
            }
        }
        repository.logOut(isLogOut)
    }

}