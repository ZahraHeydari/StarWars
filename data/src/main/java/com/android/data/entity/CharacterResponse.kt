package com.android.data.entity

data class CharacterResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<CharacterDetail>
)