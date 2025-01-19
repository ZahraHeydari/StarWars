package com.android.data.mapper

import com.android.data.entity.CharacterDetail
import com.android.data.entity.CharacterResponse
import com.android.domain.entity.CharacterDetailObject
import com.android.domain.entity.CharacterResponseObject

fun CharacterResponse.mapTo() = CharacterResponseObject(count = count,
    next = next,
    previous = previous,
    results = results.map { it.mapTo() }
)

fun CharacterDetail.mapTo() = CharacterDetailObject(
    name,
    height,
    mass,
    hair_color,
    skin_color,
    eye_color,
    birth_year,
    gender,
    homeworld,
    films,
    species,
    vehicles,
    starships,
    created,
    edited,
    url
)