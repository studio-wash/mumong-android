package com.studiowash.mumong.presentation.screen.home.model

import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity

data class TestPutEchoResult (
    val name: String,
    val id: Long
)

fun TestPutEchoResultEntity.toTestPutEchoResult() = TestPutEchoResult(name, id)