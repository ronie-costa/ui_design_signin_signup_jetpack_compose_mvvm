package com.ronieapps.cleararcteture.presentation

import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ronieapps.cleararcteture.presentation.ui.theme.ClearArctetureTheme
import com.ronieapps.cleararcteture.presentation.view.CadastroView
import com.ronieapps.cleararcteture.presentation.view.HomeView
import com.ronieapps.cleararcteture.presentation.view.LoginView
import com.ronieapps.cleararcteture.core.consts.Routes
import com.ronieapps.cleararcteture.core.utils.MessageComposable
import com.ronieapps.cleararcteture.domain.model.UserModel
import com.ronieapps.cleararcteture.presentation.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var navController: NavHostController

    private val owner: LifecycleOwner = this
    private val context: Context = this

    private lateinit var message: MutableState<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        message = mutableStateOf("")

        setContent {
            ClearArctetureTheme {
                navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.HomeView.route) {
                    composable(Routes.LoginView.route) {
                        LoginView(navController, authViewModel, owner, context)
                    }
                    composable(Routes.CadastroView.route) {
                        CadastroView(navController, authViewModel, owner, context)
                    }
                    composable(Routes.HomeView.route) {
                        HomeView(navController)
                    }
                }

                if (message.value != "") {
                    MessageComposable(
                        message = message.value,
                        onClickClear = {
                            message.value = ""
                        }
                    )
                }
            }
        }
    }


    private fun observers() {
        authViewModel.message.observe(this) {
            message.value = it
        }
        authViewModel.login.observe(this) {
            navController.navigate(Routes.HomeView.route)
        }
        authViewModel.signup.observe(this) {
            navController.navigate(Routes.HomeView.route)
        }
    }


    private fun flows() = lifecycleScope.launchWhenStarted {
        authViewModel.navControllerFlow.collect { navState ->
            when (navState) {
                AuthViewModel.UINavController.Success -> {
                    navController.navigate(Routes.HomeView.route)
                }
                AuthViewModel.UINavController.Failure -> {
                    authViewModel.setMessage("Erro ao Efetuar Login")
                    Log.d("Failure", "Erro ao Efetuar Login")
                }
                AuthViewModel.UINavController.Initial -> {
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        observers()
        flows()
    }
}