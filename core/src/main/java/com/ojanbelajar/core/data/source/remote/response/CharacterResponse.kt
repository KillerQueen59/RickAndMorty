package com.ojanbelajar.core.data.source.remote.response


data class CharacterResponse(
    val id: Int = 0,

    val name: String = "",

    val status: String = "",

    val species: String = "",

    val type: String = "",

    val gender: String = "",

    val origin: OriginResponse = OriginResponse(""),

    val location: LocationResponse = LocationResponse(""),

    val image: String = "",

    val episode: List<String> = emptyList()
)