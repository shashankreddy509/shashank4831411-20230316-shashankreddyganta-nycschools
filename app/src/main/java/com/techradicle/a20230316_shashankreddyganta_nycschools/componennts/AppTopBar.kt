package com.techradicle.a20230316_shashankreddyganta_nycschools.componennts

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@ExperimentalMaterial3Api
@Composable
fun AppTopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black
            )
        },
        colors = getTopBarColors()
    )
}

@Composable
@ExperimentalMaterial3Api
fun getTopBarColors() = TopAppBarDefaults.smallTopAppBarColors(
    containerColor = Color.White,
    navigationIconContentColor = Color.Black,
    actionIconContentColor = Color.Black
)