package com.studiowash.mumong.common.model

import java.io.Serializable

data class AttachedRecordingItem(
    val recordingSrc: String,
    val length: String,
    val type: String,
    val title: String,
    val caption: String = ""
) : Serializable