package com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techradicle.a20230316_shashankreddyganta_nycschools.componennts.ProgressBar
import com.techradicle.a20230316_shashankreddyganta_nycschools.componennts.TopAppBarWithActions
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.ATTENDANCE_RATE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.COLLAGE_CAREER_RATE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.DETAILS_SCREEN_TEXT
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.END_TIME
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.ERROR_MESSAGE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.EXTRACURRICULAR_ACTIVITIES
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.FAX
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.GRADUATION_RATE
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.LOCATION
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.NO_INFORMATION
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.OVERVIEW
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.PHONE_NUMBER
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.SCHOOL_SPORTS
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.START_TIME
import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.TOTAL_STUDENTS
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.Response
import com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.dashboard.DashboardViewModel

@ExperimentalMaterial3Api
@Composable
fun SchoolDetailsScreen(
    schoolId: String,
    navigateBack: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    //Fetching the details of a school
    LaunchedEffect(key1 = Unit) {
        viewModel.getSchoolDetails(schoolId)
    }

    Scaffold(
        topBar = {
            TopAppBarWithActions(title = DETAILS_SCREEN_TEXT, navigateBack = navigateBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val schoolDetail = viewModel.schoolDetails) {
                is Response.Failure -> Text(text = ERROR_MESSAGE)
                is Response.Loading -> ProgressBar()
                is Response.Success -> schoolDetail.data?.let { schoolDetails ->
                    val schoolData = schoolDetails.firstOrNull()
                    schoolData?.let {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Text(
                                text = schoolData.school_name!!,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 20.sp
                            )
                            RowItem(OVERVIEW, schoolData.overview_paragraph)
                            MultipleEntries(
                                TOTAL_STUDENTS,
                                COLLAGE_CAREER_RATE,
                                schoolData.total_students,
                                schoolData.college_career_rate
                            )
                            RowItem(LOCATION, schoolData.location)
                            MultipleEntries(
                                PHONE_NUMBER,
                                FAX,
                                schoolData.phone_number,
                                schoolData.fax_number
                            )

                            MultipleEntries(
                                START_TIME,
                                END_TIME,
                                schoolData.start_time,
                                schoolData.end_time
                            )
                            RowItem(title = SCHOOL_SPORTS, description = schoolData.school_sports)
                            MultipleEntries(
                                firstItemTitle = GRADUATION_RATE,
                                secondItemTitle = ATTENDANCE_RATE,
                                firstItemDescription = schoolData.graduation_rate,
                                secondItemDescription = schoolData.attendance_rate
                            )
                            RowItem(
                                title = EXTRACURRICULAR_ACTIVITIES,
                                description = schoolData.extracurricular_activities
                            )
                        }
                    } ?: Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = NO_INFORMATION,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun RowItem(title: String, description: String?) {
    description?.let {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}

@Composable
fun MultipleEntries(
    firstItemTitle: String, secondItemTitle: String,
    firstItemDescription: String?, secondItemDescription: String?
) {
    Row {
        firstItemDescription?.let {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = firstItemTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = firstItemDescription,
                    modifier = Modifier
                        .padding(top = 4.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        secondItemDescription?.let {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = secondItemTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = secondItemDescription,
                    modifier = Modifier
                        .padding(top = 4.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    }
}