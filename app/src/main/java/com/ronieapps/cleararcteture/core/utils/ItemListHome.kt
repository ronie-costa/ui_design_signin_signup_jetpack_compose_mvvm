package com.ronieapps.cleararcteture.core.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ronieapps.cleararcteture.R

@Composable
fun ItemListHome(list: MutableList<String>, position: Int) {
    var favoriteState by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
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