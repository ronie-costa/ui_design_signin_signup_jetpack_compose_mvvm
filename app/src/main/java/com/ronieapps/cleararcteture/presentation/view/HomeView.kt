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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ronieapps.cleararcteture.R


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeView(navController: NavController) {

    val list: MutableList<String> = mutableListOf(
        "Maria Silva",
        "Carlos Pereira",
        "Pedro Silva"
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.background(Color.Cyan)) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                itemsIndexed(list) { position, _ ->
                    var favoriteState by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        elevation = 5.dp,
                        shape = RoundedCornerShape(58.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = list[position],
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .align(alignment = Alignment.CenterVertically),
                            )
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box {
                                    IconButton(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .padding(vertical = 5.dp),
                                        onClick = { favoriteState = !favoriteState }
                                    ) {
                                        val painter: Painter = if (favoriteState) {
                                            painterResource(id = R.drawable.baseline_favorite_24)
                                        } else {
                                            painterResource(id = R.drawable.baseline_favorite_border_24)
                                        }
                                        Icon(
                                            painter = painter,
                                            tint = Color.Cyan,
                                            contentDescription = "",
                                        )
                                    }
                                }
                                Box {
                                    IconButton(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .padding(top = 5.dp, bottom = 5.dp, end = 10.dp),
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_clear_24),
                                            tint = Color.Red,
                                            contentDescription = "",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}