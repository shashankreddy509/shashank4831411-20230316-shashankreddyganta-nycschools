package com.techradicle.a20230316_shashankreddyganta_nycschools.domain.remote

import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.NycSchoolList
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.models.SchoolDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface NycSchoolApi {
//    https://data.cityofnewyork.us/resource/f9bf-2cp4.json
//    https://data.cityofnewyork.us/resource/s3k6-pzi2.json?dbn=01M292

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolList(): NycSchoolList

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolDetails(@Query("dbn") schoolId: String): SchoolDetails
}
