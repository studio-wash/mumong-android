package com.studiowash.mumong.domain.common.entity

import java.io.Serializable

data class AttachedImageEntity(
    val imageSrc: String,
    val caption: String? = null
) : Serializable