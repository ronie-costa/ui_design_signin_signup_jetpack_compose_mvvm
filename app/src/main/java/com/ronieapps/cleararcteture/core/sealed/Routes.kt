package com.ronieapps.cleararcteture.core.sealed

sealed class Routes(val route: String) {
    object LoginView: Routes("login_view")
    object CadastroView: Routes("cadastro_view")
    object HomeView: Routes("home_view")
}