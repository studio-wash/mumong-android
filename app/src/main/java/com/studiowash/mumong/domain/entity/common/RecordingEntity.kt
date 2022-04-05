package com.studiowash.mumong.domain.entity.common

import java.io.Serializable

data class RecordingEntity(
    val recordingSrc: String,
    val length: String,
    val type: String,
    val title: String,
    val caption: String = ""
) : Serializable