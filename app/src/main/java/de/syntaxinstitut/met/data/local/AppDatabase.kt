package de.syntaxinstitut.met.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntaxinstitut.met.data.datamodels.Artwork


@Database(entities = [Artwork::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract val appDatabaseDao: AppDatabaseDao
}

private lateinit var INSTANCE: AppDatabase

// if there's no Database a new one is built
fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .build()
        }
    }
    return INSTANCE
}