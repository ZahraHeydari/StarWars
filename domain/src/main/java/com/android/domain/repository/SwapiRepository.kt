package com.android.domain.repository

import com.android.domain.entity.CharacterDetailObject
import com.android.domain.entity.CharacterResponseObject
import com.android.domain.util.ResultOf

interface SwapiRepository {
    suspend fun fetchItems(query: String): ResultOf<CharacterResponseObject>
    suspend fun getCharacterDetail(url: String): ResultOf<CharacterDetailObject>
}