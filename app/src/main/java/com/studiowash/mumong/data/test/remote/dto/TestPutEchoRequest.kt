package com.studiowash.mumong.data.test.remote.dto

import com.google.gson.annotations.SerializedName

data class TestPutEchoRequest(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Long
)