package com.studiowash.mumong.domain.common

import java.io.Serializable

data class RecordingItem(
    val recordingSrc: String,
    val length: String,
    val type: String,
    val title: String,
    val caption: String = ""
) : Serializable