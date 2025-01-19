package com.android.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponseObject(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<CharacterDetailObject> = emptyList()
) : Parcelable