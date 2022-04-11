package com.studiowash.mumong.data.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    @SerialName("user_id") val userId: Int,
    @SerialName("email") val email: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("provider_id") val providerId: String,
    @SerialName("user_token") val userToken: String,
    @SerialName("profile_img") val profileImg: String,
    @SerialName("description") val description: String
)