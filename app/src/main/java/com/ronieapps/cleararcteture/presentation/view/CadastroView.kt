package com.ronieapps.cleararcteture.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ronieapps.cleararcteture.R
import com.ronieapps.cleararcteture.domain.model.UserModel
import com.ronieapps.cleararcteture.presentation.ui.theme.Purple500
import com.ronieapps.cleararcteture.presentation.view_model.AuthViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CadastroView(
    navController: NavController,
    authViewModel: AuthViewModel,
    owner: LifecycleOwner,
    context: Context
) {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    var passwordStateVisible by remember {
        mutableStateOf(false)
    }
    var confirmPasswordStateVisible by remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.background(Color.Cyan)) {

            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                modifier = Modifier.padding(0.dp),
                backgroundColor = Color.Cyan,
                shape = RoundedCornerShape(0.dp),
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                        .padding(end = 3.dp),
                    tint = Purple500
                )
            }

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.background(Color.Cyan)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 365.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_camera_24),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }

                Column {

                    Text(
                        text = "Criar Conta",
                        modifier = Modifier.padding(15.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .fillMaxWidth()
                            .background(Purple500)
                    )

                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .verticalScroll(state = ScrollState(1))
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            label = { Text(text = "Nome") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(0.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            label = { Text("E-mail") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(0.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = { Text("Senha") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(0.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            visualTransformation =
                            if (passwordStateVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            trailingIcon = {
                                val painter =
                                    if (passwordStateVisible)
                                        painterResource(id = R.drawable.baseline_visibility_off_24)
                                    else
                                        painterResource(id = R.drawable.baseline_visibility_24)

                                val description =
                                    if (passwordStateVisible)
                                        "Esconder Senha!"
                                    else
                                        "Mostrar Senha!"

                                IconButton(onClick = {
                                    passwordStateVisible = !passwordStateVisible
                                }) {
                                    Icon(painter = painter, contentDescription = description)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                            },
                            label = { Text("Confirmar Senha") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(0.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation =
                            if (confirmPasswordStateVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            trailingIcon = {
                                val painter =
                                    if (confirmPasswordStateVisible)
                                        painterResource(id = R.drawable.baseline_visibility_off_24)
                                    else
                                        painterResource(id = R.drawable.baseline_visibility_24)

                                val description =
                                    if (confirmPasswordStateVisible)
                                        "Hide password"
                                    else
                                        "Show password"

                                IconButton(onClick = {
                                    confirmPasswordStateVisible = !confirmPasswordStateVisible
                                }) {
                                    Icon(painter = painter, contentDescription = description)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .height(55.dp),
                            shape = RoundedCornerShape(0.dp),
                            onClick = {
                                val userModel = UserModel(
                                    name = name,
                                    email = email,
                                    password = password,
                                    confirmPassword = confirmPassword
                                )
                                authViewModel.startSignUp(userModel)
                            },
                        ) {
                            Text("Criar Conta")
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }

    }
}