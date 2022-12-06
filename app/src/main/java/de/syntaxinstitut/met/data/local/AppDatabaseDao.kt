package de.syntaxinstitut.met.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.met.data.datamodels.Artwork


@Dao
interface AppDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artwork: List<Artwork>)

    @Query("SELECT * from Artwork")
    fun getAll(): LiveData<List<Artwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artwork: Artwork)

    @Delete
    suspend fun delete(artwork: Artwork)
}