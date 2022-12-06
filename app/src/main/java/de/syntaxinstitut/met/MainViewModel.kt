package de.syntaxinstitut.met

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.met.data.AppRepository
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.data.local.getDatabase
import de.syntaxinstitut.met.data.model.Datasource
import de.syntaxinstitut.met.data.model.Exhibition
import de.syntaxinstitut.met.data.remote.MuseumApi
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Das MainViewModel
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    /* -------------------- Klassen Variablen -------------------- */
    val database = getDatabase(application)
    val repository: AppRepository = AppRepository(MuseumApi, database)
    var image = repository.image
    val collection = repository.collection


    private val allImages = loadJson(R.raw.allimages)
    private val highlights = loadJson(R.raw.highlights)

    /** JSON File created because of missing Artworks in API*/

    private fun loadJson(json: Int): List<String> {
        val raw = getApplication<Application>().resources.openRawResource(json).bufferedReader()
            .use { it.readText() }
        val arr = JSONObject(raw).get("imageIDs")
        val imagesString = arr.toString().substring(1, arr.toString().length - 1)
        return listOf(*imagesString.split(",").toTypedArray()).shuffled()
    }

    fun loadAllObjectIds() {
        viewModelScope.launch {
            repository.getObjectIds()
        }
    }

    fun loadRandomObject() {
        viewModelScope.launch {
            repository.getObjectById(allImages.random().toString())
            //repository.getObjectById(highlights.random().toString())
        }
    }

    fun loadExhibitionById(id: Int): Exhibition? {
        return repository.getExhibitionByID(id)
    }

    fun insertArtwork(artwork: Artwork) {
        viewModelScope.launch {
            repository.insert(artwork)
        }

    }

    fun deleteArtwork(artwork: Artwork) {
        viewModelScope.launch {
            repository.deleteArtwork(artwork)
        }
    }

}
	
    /** Signal um zu signalisieren, dass zum zweiten Fragment gewechselt werden soll */
    var navigateToFragmentTwo = MutableLiveData(false)

    /* -------------------- Öffentliche Funktionen -------------------- */

    /**
     * Mit dieser Funktion wird der Trigger ausgelöst um zum zweiten Fragment zu wechseln
     */
    fun navigateToFragmentTwo() {
        navigateToFragmentTwo.value = true
    }

    /**
     * Setzt alle Werte der Variablen auf ihren "Werkszustand" zurück
     */
    fun resetAllValues() {
        navigateToFragmentTwo.value = false
    }

