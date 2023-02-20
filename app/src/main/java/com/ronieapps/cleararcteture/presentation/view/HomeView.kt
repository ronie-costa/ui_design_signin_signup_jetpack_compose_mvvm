package com.ronieapps.cleararcteture.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.ronieapps.cleararcteture.core.sealed.AuthState
import com.ronieapps.cleararcteture.core.sealed.Routes
import com.ronieapps.cleararcteture.core.utils.ItemListHome
import com.ronieapps.cleararcteture.presentation.view_model.AuthViewModel

@SuppressLint(
    "StateFlowValueCalledInComposition",
    "CoroutineCreationDuringComposition"
)
@Composable
fun HomeView(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val list: MutableList<String> = mutableListOf(
        "Maria Silva",
        "Carlos Pereira",
        "Pedro Silva",
    )

    Column(
        Modifier
            .background(Color.Cyan)
            .fillMaxSize()
    ) {

        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                itemsIndexed(list) { position, _ ->
                    ItemListHome(list = list, position = position)
                }
            }
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 20.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(50.dp),
                onClick = {
                    authViewModel.logOut()
                    navController.popBackStack()
                }
            ) { Text("SAIR", color = Color.White) }
        }


    }


}