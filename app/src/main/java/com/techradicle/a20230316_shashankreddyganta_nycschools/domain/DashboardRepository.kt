package com.techradicle.a20230316_shashankreddyganta_nycschools.domain

import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.NycSchoolList
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.Response
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.SchoolDetails

interface DashboardRepository {

    suspend fun getSchoolList(): Response<NycSchoolList>

    suspend fun getSchoolDetails(schoolId: String): Response<SchoolDetails>
}