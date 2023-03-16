package com.techradicle.a20230316_shashankreddyganta_nycschools.di

import com.techradicle.a20230316_shashankreddyganta_nycschools.core.AppConstants.BASE_URL
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.DashboardRepository
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.DashboardRepositoryImpl
import com.techradicle.a20230316_shashankreddyganta_nycschools.domain.remote.NycSchoolApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesOcrApi(): NycSchoolApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NycSchoolApi::class.java)
    }

    @Provides
    fun provideDashboardRespository(nycAPi: NycSchoolApi): DashboardRepository =
        DashboardRepositoryImpl(api = nycAPi)

}