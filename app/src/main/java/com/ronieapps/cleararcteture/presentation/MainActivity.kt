package com.ronieapps.cleararcteture.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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

    private lateinit var authViewModel: AuthViewModel
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val owner: LifecycleOwner = this
        val context: Context = this

        val message = mutableStateOf("")

        val messageObserver = Observer<String> { message.value = it }
        val loginObserver = Observer<UserModel> { navController.navigate(Routes.HomeView.route) }
        val signupObserver = Observer<UserModel> { navController.navigate(Routes.HomeView.route) }

        setContent {
            ClearArctetureTheme {
                navController = rememberNavController()
                authViewModel = hiltViewModel()

                NavHost(navController = navController, startDestination = Routes.LoginView.route) {
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

                authViewModel.message.observe(this, messageObserver)
                authViewModel.login.observe(this, loginObserver)
                authViewModel.signup.observe(this, signupObserver)
            }
        }
    }
}