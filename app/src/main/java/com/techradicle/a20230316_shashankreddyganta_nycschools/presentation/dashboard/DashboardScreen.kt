package com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techradicle.a20230316_shashankreddyganta_nycschools.componennts.AppTopBar
import com.techradicle.a20230316_shashankreddyganta_nycschools.componennts.ProgressBar
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.DASHBOARD
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.ERROR_MESSAGE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.MATH_AVERAGE_SCORE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.SAT_TEST_TAKERS
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.NycSchoolListItem
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.Response

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
    navigateToSchoolDetailsScreen: (schoolId: String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    //Fetching the Data when the composable is rendered.
    LaunchedEffect(key1 = Unit) {
        viewModel.getSchoolList()
    }

    Scaffold(
        topBar = { AppTopBar(DASHBOARD) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (val schoolList = viewModel.schoolList) {
                is Response.Failure -> Text(text = ERROR_MESSAGE)
                is Response.Loading -> ProgressBar()
                is Response.Success -> schoolList.data?.let { schoolList ->
                    LazyColumn {
                        items(schoolList.size) { index ->
                            SchoolItem(schoolList[index], navigateToSchoolDetailsScreen)
                        }
                    }
                }
            }
        }
    }
}


//This composable is used to render single entry from the list of school entries.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolItem(
    nycSchoolListItem: NycSchoolListItem,
    navigateToSchoolDetailsScreen: (schoolId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White),
        onClick = { navigateToSchoolDetailsScreen(nycSchoolListItem.dbn) }
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = nycSchoolListItem.school_name,
                maxLines = 1,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(
                    text = "$SAT_TEST_TAKERS ${nycSchoolListItem.num_of_sat_test_takers}",
                    maxLines = 1,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$MATH_AVERAGE_SCORE ${nycSchoolListItem.sat_math_avg_score}",
                    maxLines = 1,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}