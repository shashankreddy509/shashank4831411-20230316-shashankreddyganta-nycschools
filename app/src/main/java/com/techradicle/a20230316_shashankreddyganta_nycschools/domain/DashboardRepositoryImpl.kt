package com.techradicle.a20230316_shashankreddyganta_nycschools.domain

import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.NycSchoolList
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.Response
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.SchoolDetails
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.remote.NycSchoolApi
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val api: NycSchoolApi
) : DashboardRepository {
    override suspend fun getSchoolList(): Response<NycSchoolList> {
        return try {
            val response = api.getSchoolList()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getSchoolDetails(schoolId: String): Response<SchoolDetails> {
        return try {
            val response = api.getSchoolDetails(schoolId)
            Response.Success(response)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


}