package com.studiowash.mumong.domain.login.usecase

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginAuthType
import com.studiowash.mumong.domain.login.entity.UserEntity
import com.studiowash.mumong.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpadteOauthLoginInfoCase @Inject constructor(private val repository: LoginRepository)  {
    suspend operator fun invoke(loginAuthType: LoginAuthType, token: String): Flow<BaseResult<UserEntity, Throwable>> {
        return repository.updateOauthLoginInfo(loginAuthType, token)
    }
}