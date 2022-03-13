package com.studiowash.mumong.singleton

import com.studiowash.mumong.common.model.User

object LoginStatus {
    var currentUser: User? =
        User(
            "sechiyo97@daum.net",
            "별으잉",
            "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png",
            "샘플 자기소개"
        )
}