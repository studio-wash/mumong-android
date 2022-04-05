package com.studiowash.mumong.domain.entity.common

import java.io.Serializable

data class AttachedImageEntity(
    val imageSrc: String,
    val caption: String? = null
) : Serializable