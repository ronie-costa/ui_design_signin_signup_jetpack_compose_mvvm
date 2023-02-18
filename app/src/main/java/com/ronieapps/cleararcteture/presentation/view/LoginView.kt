package com.ronieapps.cleararcteture.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.ronieapps.cleararcteture.R
import com.ronieapps.cleararcteture.core.sealed.Routes
import com.ronieapps.cleararcteture.core.domain.model.UserModel
import com.ronieapps.cleararcteture.presentation.ui.theme.Purple500
import com.ronieapps.cleararcteture.presentation.view_model.AuthViewModel


@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun LoginView(
    navController: NavController,
    authViewModel: AuthViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordStateVisible by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.background(Color.Cyan),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 235.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_camera_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }

            Column {
                Text(
                    "Login",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 20.dp, start = 20.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Column(
                    modifier = Modifier.background(Color.White),
                    horizontalAlignment = Alignment.End
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Purple500)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = {
                            Text("E-mail")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(55.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = {
                            Text(text = "Senha")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(55.dp),
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

                            IconButton(onClick = { passwordStateVisible = !passwordStateVisible }) {
                                Icon(painter = painter, contentDescription = description)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextButton(
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        onClick = { navController.navigate(Routes.CadastroView.route) },
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Text(
                            "ainda não tem uma conta? Cadastre-se",
                            modifier = Modifier.padding(horizontal = 0.dp),
                            color = Color.Blue,
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple500
                        ),
                        shape = RoundedCornerShape(0.dp),
                        onClick = {
                            val user = UserModel(
                                email = email,
                                password = password
                            )
                            authViewModel.startLogin(user)
                        }
                    ) {
                        Text(text = "Login", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}