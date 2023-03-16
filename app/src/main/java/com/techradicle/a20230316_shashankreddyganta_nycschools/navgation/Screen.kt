package com.techradicle.a20230316_shashankreddyganta_nycschools.navgation

import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.DASHBOARD_SCREEN
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.DETAILS_SCREEN


sealed class Screen(val route: String) {
    //Dashboard Screen
    object DashboardScreen : Screen(DASHBOARD_SCREEN)

    //School Details Screen
    object SchoolDetailsScreen : Screen(DETAILS_SCREEN)
}