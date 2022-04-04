package com.studiowash.mumong.domain.common

import java.io.Serializable

data class AttachedImageItem(
    val imageSrc: String,
    val caption: String? = null
) : Serializable