package com.studiowash.mumong.domain.model.common

import java.io.Serializable

data class AttachedImageItem(
    val imageSrc: String,
    val caption: String? = null
) : Serializable