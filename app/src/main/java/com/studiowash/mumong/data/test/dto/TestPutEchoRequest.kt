package com.studiowash.mumong.data.test.dto

import com.google.gson.annotations.SerializedName

data class TestPutEchoRequest(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)