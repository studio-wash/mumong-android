package com.studiowash.mumong.data.login

import com.studiowash.mumong.data.NetworkModule
import com.studiowash.mumong.data.login.repository.LoginRepositoryImpl
import com.studiowash.mumong.domain.login.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Provides
    fun provideLoginRepository() : LoginRepository {
        return LoginRepositoryImpl()
    }
}