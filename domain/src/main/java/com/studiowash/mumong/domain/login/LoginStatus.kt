package com.studiowash.mumong.domain.login

import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.login.entity.UserEntity

object LoginStatus {
    var currentUser: UserEntity? =
        UserEntity(
            "sechiyo97@daum.net",
            "별으잉",
            Constants.sample_image_url,
            "샘플 자기소개"
        )
}