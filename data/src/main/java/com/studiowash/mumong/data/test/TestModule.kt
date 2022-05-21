package com.studiowash.mumong.data.test

import com.studiowash.mumong.data.NetworkModule
import com.studiowash.mumong.data.test.remote.api.TestApi
import com.studiowash.mumong.data.test.repository.TestRepositoryImpl
import com.studiowash.mumong.domain.test.repository.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class TestModule {
    @Provides
    fun provideTestApi(retrofit: Retrofit) : TestApi {
        return retrofit.create(TestApi::class.java)
    }

    @Provides
    fun provideTestRepository(testApi: TestApi) : TestRepository {
        return TestRepositoryImpl(testApi)
    }
}