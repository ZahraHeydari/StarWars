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
    override suspend fun fetchItems(query: String): Flow<ResultOf<CharacterResponseObject>> {
        return flow {
            emit(ResultOf.Loading())
            try {
                val response = dataService.searchCharacters(query).mapTo()
                emit(ResultOf.Success(response))
            } catch (e: Exception) {
                emit(ResultOf.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCharacterDetail(url: String): Flow<ResultOf<CharacterDetailObject>> {
        return flow {
            emit(ResultOf.Loading())
            try {
                val response = dataService.getCharacterDetail(url).mapTo()
                emit(ResultOf.Success(response))
            } catch (e: Exception) {
                emit(ResultOf.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}