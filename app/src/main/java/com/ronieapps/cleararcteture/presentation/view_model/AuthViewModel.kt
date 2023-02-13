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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

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
            else -> {
                val isSuccess: (user: UserModel) -> Unit = {
                    _login.value = it
                }
                val isFailure: (message: String) -> Unit = {
                    _message.value = it
                }
                repository.startLoginRepo(user, isSuccess, isFailure)
            }
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
                val isSuccess: (user: UserModel) -> Unit = {
                    _signup.value = it
                }
                val isFailure: (message: String) -> Unit = {
                    _message.value = "Erro ao criar conta, Tente novamente."
                }
                repository.startSignupRepo(user, isSuccess, isFailure)
            }
        }
    }

    fun setMessage(message: String) {
        _message.value = message
    }


    private val _navControllerFlow = MutableStateFlow<UINavController>(UINavController.Initial)
    val navControllerFlow: StateFlow<UINavController> get() = _navControllerFlow

    private var error = false

    fun navigationController() = viewModelScope.launch {
        delay(2000L)
        error = !error
        _navControllerFlow.value = if (error) UINavController.Failure else UINavController.Success
    }

    sealed class UINavController {
        object Success : UINavController()
        object Failure : UINavController()
        object Initial : UINavController()
    }

    fun getUser() = viewModelScope.launch {
        repository.flowUserRepo.collect {
            _user.value = it
        }
    }

}