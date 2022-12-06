package de.syntaxinstitut.met.data.datamodels


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Artwork(
    @Json(name = "objectID")
    @PrimaryKey
    val objectID: Int,
    @Json(name = "primaryImage")
    val primaryImage: String,
    @Json(name = "artistDisplayName")
    val artistName: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "culture")
    val culture: String,
    @Json(name = "period")
    val period: String
)