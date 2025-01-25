package com.android.data.repository

import com.android.data.SwapiDataService
import com.android.data.mapper.mapTo
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.entity.CharacterResponseObject
import com.android.domain.util.ResultOf
import com.android.domain.repository.SwapiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SwapiRepositoryImp(private val dataService: SwapiDataService) : SwapiRepository {
    override suspend fun fetchItems(query: String): ResultOf<CharacterResponseObject> {
        return try {
            val response = dataService.searchCharacters(query).mapTo()
            ResultOf.Success(response)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }
    }

    override suspend fun getCharacterDetail(id: String): ResultOf<CharacterDetailObject> {
        return try {
            val response = dataService.getCharacterDetail(id).mapTo()
            ResultOf.Success(response)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }
    }
}