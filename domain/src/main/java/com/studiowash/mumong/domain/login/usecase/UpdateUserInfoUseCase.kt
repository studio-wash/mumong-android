package com.studiowash.mumong.domain.login.usecase

import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginStatus
import com.studiowash.mumong.domain.login.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor() {
    suspend operator fun invoke(token: String): Flow<BaseResult<UserEntity, Throwable>> {
        return flow {
            val user = UserEntity(
                "sechiyo97@daum.net",
                "이세희",
                "별으잉",
                Constants.sample_image_url,
                "샘플 자기소개"
            )
            LoginStatus.currentUser = user
            emit(BaseResult.Success(user))
        }
    }
}