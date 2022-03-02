package com.studiowash.mumong.common

data class AttachedRecordingItem(
    val recordingSrc: String,
    val length: String,
    val type: String,
    val title: String,
    val caption: String = ""
)