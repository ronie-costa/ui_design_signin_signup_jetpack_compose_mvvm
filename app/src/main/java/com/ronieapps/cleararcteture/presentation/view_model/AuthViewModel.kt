package com.ronieapps.cleararcteture.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ronieapps.cleararcteture.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieapps.cleararcteture.domain.listener.AuthLoginListener
import com.ronieapps.cleararcteture.domain.listener.AuthSignupListener
import com.ronieapps.cleararcteture.domain.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _login = MutableLiveData<UserModel>()
    val login: LiveData<UserModel> = _login

    private val _signup = MutableLiveData<UserModel>()
    val signup: LiveData<UserModel> = _signup

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _user = MutableStateFlow(UserModel())
    val user = _user.asStateFlow()


    fun startLogin(user: UserModel) {
        when {
            user.email.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar login"
            }
            user.password.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar login"
            }
            user.password.length <= 5 -> {
                _message.value = "Digite no minímo 6 caracteres para efetuar login"
            }
            else -> repository.startLoginRepo(user, object : AuthLoginListener {
                override fun onSuccess(user: UserModel) {
                    _login.value = user
                }

                override fun onFailed(message: String) {
                    _message.value = message
                }
            })
        }
    }

    fun startSignUp(user: UserModel) {
        when {
            user.name.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar o casdastro"
            }
            user.email.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar o casdastro"
            }
            user.password.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar o casdastro"
            }
            user.confirmPassword.isEmpty() -> {
                _message.value = "Preencha todos os campos para efetuar o casdastro"
            }
            user.password.length <= 5 -> {
                _message.value = "Digite uma senha com mais de 5 carecteres."
            }
            user.password != user.confirmPassword -> {
                _message.value = "Campo Senha precisa ser igua Confirmar Senha"
            }
            else -> {
                repository.startSignupRepo(user, object : AuthSignupListener {
                    override fun signupSuccess(user: UserModel) {
                        _signup.value = user
                    }
                    override fun signupFailure(message: String) {
                        _message.value = "Erro ao criar conta, Tente novamente."
                    }
                })
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            repository.flowUserRepo.collect {
                _user.value = it
            }
        }
    }
}