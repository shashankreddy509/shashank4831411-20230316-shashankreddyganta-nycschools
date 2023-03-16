package com.techradicle.a20230316_shashankreddyganta_nycschools.navgation

import androidx.navigation.NavController

class Direction(navController: NavController) {

    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(Screen.DashboardScreen.route)
    }

    val navigateToSchoolDetailsScreen: (String) -> Unit = { receiptId ->
        navController.navigate("${Screen.SchoolDetailsScreen.route}/$receiptId")
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}