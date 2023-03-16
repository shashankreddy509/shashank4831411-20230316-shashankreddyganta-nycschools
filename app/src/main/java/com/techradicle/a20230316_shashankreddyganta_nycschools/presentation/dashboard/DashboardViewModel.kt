package com.techradicle.a20230316_shashankreddyganta_nycschools.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.DashboardRepository
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.NycSchoolList
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.Response
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.SchoolDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository,
) : ViewModel() {
    var schoolList by mutableStateOf<Response<NycSchoolList>>(Response.Success(null))

    var schoolDetails by mutableStateOf<Response<SchoolDetails>>(Response.Success(null))

    fun getSchoolList() = viewModelScope.launch {
        schoolList = Response.Loading
        schoolList = repository.getSchoolList()
    }

    fun getSchoolDetails(schoolId: String) = viewModelScope.launch {
        schoolDetails = Response.Loading
        schoolDetails = repository.getSchoolDetails(schoolId)
    }
}