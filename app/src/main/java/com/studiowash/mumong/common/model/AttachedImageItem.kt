package com.studiowash.mumong.common.model

import java.io.Serializable

data class AttachedImageItem(
    val imageSrc: String,
    val caption: String? = null
) : Serializable