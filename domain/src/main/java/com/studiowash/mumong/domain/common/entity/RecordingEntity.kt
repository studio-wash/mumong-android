package com.studiowash.mumong.domain.common.entity

import java.io.Serializable

data class RecordingEntity(
    val recordingSrc: String,
    val length: String,
    val type: String,
    val title: String,
    val caption: String = ""
) : Serializable