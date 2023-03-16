package com.techradicle.a20230316_shashankreddyganta_nycschools.navgation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.NO_VALUE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.SCHOOL_ID
import com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.dashboard.DashboardScreen
import com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.details.SchoolDetailsScreen

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun NavGraph(
    navController: NavHostController
) {
    //This variable is used to store the present direction been used and using this variable
    // the navigation is made.
    val direction = remember(navController) { Direction(navController) }

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.DashboardScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen(
                navigateToSchoolDetailsScreen = { schoolId ->
                    direction.navigateToSchoolDetailsScreen(schoolId)
                }
            )
        }
        composable(route = "${Screen.SchoolDetailsScreen.route}/{$SCHOOL_ID}",
            arguments = listOf(
                navArgument(SCHOOL_ID) {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val schoolId = backStackEntry.arguments?.getString(SCHOOL_ID) ?: NO_VALUE
            SchoolDetailsScreen(
                schoolId = schoolId,
                navigateBack = { direction.navigateBack() }
            )
        }
    }
}