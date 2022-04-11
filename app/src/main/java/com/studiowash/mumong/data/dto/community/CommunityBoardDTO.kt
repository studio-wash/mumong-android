package com.studiowash.mumong.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityBoardDTO (
    @SerialName("board_id") val boardId: Int,
    @SerialName("board_name") val boardName: String,
    @SerialName("description") val description: String
)