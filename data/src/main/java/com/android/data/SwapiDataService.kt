package com.android.data

import com.android.data.entity.CharacterDetail
import com.android.data.entity.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiDataService {

    @GET("/api/people")
    suspend fun searchCharacters(
        @Query("search") query: String
    ): CharacterResponse

    @GET("/api/people/{id}/")
    suspend fun getCharacterDetail(
        @Path("id") id : String
    ): CharacterDetail
}