package com.studiowash.mumong.data.test.remote.dto

import com.google.gson.annotations.SerializedName
import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity

data class TestPutEchoResultDTO(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
) {
    fun toTestPutEchoResultEntity() = TestPutEchoResultEntity(name, id)
}