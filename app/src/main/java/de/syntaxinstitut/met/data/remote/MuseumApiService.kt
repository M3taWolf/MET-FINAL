package de.syntaxinstitut.met.data.remote


import android.media.Image
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.met.data.datamodels.ApiResponse
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.data.datamodels.ArtworkList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MuseumApiService {

    @GET("objects")
    suspend fun getAllObjectIds(): ArtworkList
    //suspend fun getArtworkList(@Path("objectID") objectID: String): Artwork

    @GET("objects/{objectID}")
    suspend fun getObjectById(@Path("objectID") objectID: String): Artwork

}

object MuseumApi {
    val retrofitService: MuseumApiService by lazy { retrofit.create(MuseumApiService::class.java) }
}