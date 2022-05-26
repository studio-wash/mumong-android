package com.studiowash.mumong.domain.login.usecase

import android.content.Context
import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import com.studiowash.mumong.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestKakaoTalkLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(context: Context): Flow<RequestResult<LoginResultEntity>> {
        return repository.requestKakaoTalkLogin(context)
    }
}