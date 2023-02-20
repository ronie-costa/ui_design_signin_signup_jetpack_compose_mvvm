package com.ronieapps.cleararcteture.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ronieapps.cleararcteture.presentation.ui.theme.ClearArctetureTheme
import com.ronieapps.cleararcteture.presentation.view.CadastroView
import com.ronieapps.cleararcteture.presentation.view.HomeView
import com.ronieapps.cleararcteture.presentation.view.LoginView
import com.ronieapps.cleararcteture.core.sealed.Routes
import com.ronieapps.cleararcteture.core.sealed.AuthState
import com.ronieapps.cleararcteture.core.utils.MessageComposable
import com.ronieapps.cleararcteture.presentation.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
@SuppressLint("CoroutineCreationDuringComposition")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var navController: NavHostController

    private lateinit var message: MutableState<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        message = mutableStateOf("")

        setContent {
            ClearArctetureTheme {
                navController = rememberNavController()
                authViewModel = hiltViewModel()

                lifecycleScope.launch {
                    authViewModel.message.collect {
                        message.value = it
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Routes.LoginView.route,
                    modifier = Modifier.background(Color.Magenta)
                ) {
                    composable(route = Routes.LoginView.route) {
                        LoginView(navController, authViewModel)
                    }
                    composable(Routes.CadastroView.route) {
                        CadastroView(navController, authViewModel)
                    }
                    composable(Routes.HomeView.route) {
                        HomeView(navController, authViewModel)
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

}