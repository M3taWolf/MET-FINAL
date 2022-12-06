package de.syntaxinstitut.met.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.data.local.AppDatabase
import de.syntaxinstitut.met.data.model.Datasource
import de.syntaxinstitut.met.data.model.Exhibition
import de.syntaxinstitut.met.data.remote.MuseumApi
import kotlin.Exception

//class Repository(private val api: MuseumApi) {

//private val _artwork = MutableLiveData<List<Artwork>>()
//val artwork: LiveData<List<Artwork>>
//get() = _artwork

//suspend fun getData() {
//_artwork.value = api.retrofitService.getData().data.memes
// }

//}


//const val TAG = "AppRepository"

//class AppRepository(private val api: MuseumApi, private val database: AppDatabase) {

// das was wir auch wirklich in der App sehen
//val artworkList = database.appDatabaseDao.getAll()

// ohne Repository Pattern
//    private val _artworkList = MutableLiveData<List<Artwork>>()
//    val artworkList: LiveData<List<Artwork>>
//    get() = _artworkList

//suspend fun getMemes() {
// wichtig damit die LiveData Zeit hat zu laden bevor der API Call scheitert
//withContext(Dispatchers.IO) {

//println("WTF call API")

//val newArtworkList = api.retrofitService.getArtworkList().data.artwork

// println("WTF called API")

// database.appDatabaseDao.insertAll(newArtworkList)

//println("WTF saved data")
//}
//}
//}

const val TAG = "AppRepository"

/**
 * Diese Klasse holt die Informationen und stellt sie mithilfe von Live Data dem Rest
 * der App zur Verfügung
 */
class AppRepository(private val api: MuseumApi, private val database: AppDatabase) {

    // Die LiveData Variable memes enthält die Liste aus dem API call
    private val _objectIdList = MutableLiveData<List<Int>>()
    val objectIdList: LiveData<List<Int>>
        get() = _objectIdList

    val collection = database.appDatabaseDao.getAll()

    private val _image = MutableLiveData<Artwork>()
    val image: LiveData<Artwork>
        get() = _image

    suspend fun insert(artwork: Artwork) {
        try {
            database.appDatabaseDao.insert(artwork)
        }catch (e:Exception) {
            Log.e(TAG, "Couldn't insert artwork: $e")
        }

    }

    suspend fun deleteArtwork(artwork: Artwork) {
        try {
            database.appDatabaseDao.delete(artwork)
        }catch (e:Exception) {
            Log.e(TAG, "Couldn't delete artwork: $e")
        }
    }


    /**
     * Diese Funktion ruft die Daten aus dem API Service ab und speichert die Antwort in der
     * Variable memes. Falls der Call nicht funktioniert, wird die Fehlermeldung geloggt
     */
    suspend fun getObjectIds() {
        try {
            _objectIdList.value = api.retrofitService.getAllObjectIds().allObjectIds
        } catch (e: Exception) {
            Log.e(TAG, "Error loading all data from API: $e")
        }
    }

    suspend fun getObjectById(objectId: String) {
        try {
            val result = api.retrofitService.getObjectById(objectId)
            _image.value = result
        } catch (e: Exception) {
            Log.e(TAG, "Error loading image with Id: $objectId from API: $e")
        }

    }

    fun getExhibitionByID(id: Int): Exhibition? {
        val filterList = Datasource().exhibitionList.filter {
            it.id == id
        }

        return if (filterList.isEmpty())
            null
        else filterList.first()

    }

}

