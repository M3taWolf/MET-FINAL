package de.syntaxinstitut.met.data.datamodels

import com.squareup.moshi.Json

data class ArtworkList (
    @Json(name = "objectIDs")
    val allObjectIds: List<Int> = listOf()
)