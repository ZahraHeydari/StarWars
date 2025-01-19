package com.android.data

import com.android.data.entity.CharacterDetail
import com.android.data.entity.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SwapiDataService {

    @GET("/api/people")
    suspend fun searchCharacters(
        @Query("search") query: String
    ): CharacterResponse

    @GET
    suspend fun getCharacterDetail(
        @Url url: String
    ): CharacterDetail
}