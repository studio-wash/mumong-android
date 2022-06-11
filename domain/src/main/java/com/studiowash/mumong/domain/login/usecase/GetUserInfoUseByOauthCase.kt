package com.studiowash.mumong.domain.login.usecase

import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginAuthType
import com.studiowash.mumong.domain.login.LoginStatus
import com.studiowash.mumong.domain.login.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserInfoUseByOauthCase @Inject constructor() {
    suspend operator fun invoke(loginAuthType: LoginAuthType, token: String): Flow<BaseResult<UserEntity, Throwable>> {
        return flow {
            val token = "TEST_TOKEN" // TODO
            val user = UserEntity(
                "sechiyo97@daum.net",
                "이세희",
                "별으잉",
                Constants.sample_image_url,
                "샘플 자기소개"
            )
            LoginStatus.currentUser = user
            LoginStatus.currentToken = token
            emit(BaseResult.Success(user))
        }
    }
}