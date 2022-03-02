package com.studiowash.mumong.common

data class AttachedImageItem(
    val imageSrc: String,
    val width: Int,
    val height: Int,
    val caption: String? = null
)