package com.ronieapps.cleararcteture.core.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ronieapps.cleararcteture.R


@Composable
fun MessageComposable(message: String, onClickClear: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().background(Color.Red)) {

        Text(
            text = message,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterStart)
                .padding(top = 15.dp, bottom = 15.dp, start = 10.dp, end = 48.dp)
        )

        IconButton(
            modifier = Modifier.padding(vertical = 5.dp).align(alignment = Alignment.CenterEnd),
            onClick = onClickClear
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_clear_24),
                contentDescription = "",
                tint = Color.White
            )
        }

    }
}