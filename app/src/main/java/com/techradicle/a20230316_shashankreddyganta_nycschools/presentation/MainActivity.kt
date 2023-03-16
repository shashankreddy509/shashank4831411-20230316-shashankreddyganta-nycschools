package com.techradicle.a20230316_shashankreddyganta_nycschools.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.techradicle.a20230316_shashankreddyganta_nycschools.navgation.NavGraph
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //Adding a navController variable
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Storing the navController in remember state so the state is saved.
            navController = rememberAnimatedNavController()
            //Creating the Navigation graph and passing the navController to NavGraph class where
            //all the navigation take place.
            NavGraph(navController = navController)
        }
    }
}