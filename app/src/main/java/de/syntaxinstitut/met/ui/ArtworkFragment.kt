package de.syntaxinstitut.met.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.squareup.picasso.Picasso
import de.syntaxinstitut.met.MainViewModel
import de.syntaxinstitut.met.R
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.databinding.FragmentArtworkBinding
import kotlinx.coroutines.NonCancellable.start


/**
 * Fragment 2
 */
class ArtworkFragment : Fragment() {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentArtworkBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: MainViewModel by activityViewModels()

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtworkBinding.inflate(inflater)
        viewModel.loadRandomObject()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var image: Artwork? = null

        binding.btnNextArtwork.setOnClickListener {
            viewModel.loadRandomObject()
            binding.btnAdd.setImageResource(R.drawable.ic_outline_add_to_photos_24)


        }


       binding.btnAdd.setOnClickListener {


           if (image != null) {
               viewModel.insertArtwork(image!!)
               binding.btnAdd.setImageResource(R.drawable.ic_baseline_library_add_24)
           }
       }

        viewModel.image.observe(
            viewLifecycleOwner, Observer {
                image = it
                loadImage(image!!)
            }
        )

    }

    private fun fader() {

        binding.btnAdd.alpha = 0f
        var toAlpha = 1f

        val animator = ObjectAnimator.ofFloat(binding.btnAdd, View.ALPHA, toAlpha)
        animator.duration = 400
        animator.start()


    }
    private fun fader1() {

        binding.btnNextArtwork.alpha = 0f
        var toAlpha1 = 1f

        val animator1 = ObjectAnimator.ofFloat(binding.btnNextArtwork, View.ALPHA, toAlpha1)
        animator1.duration = 200
        animator1.start()

    }



    fun loadImage(image: Artwork) {
        Picasso.get().load(image.primaryImage).into(binding.artworkImg);
        if (image.title != "") {
            binding.titleTxt.text = image.title
        } else {
            binding.titleTxt.text = "Unknown Title"
        }
        if (image.artistName != "") {
            binding.nameTxt.text = image.artistName
        } else {
            binding.nameTxt.text = "Unknown Artist"
        }
        if (image.period != "") {
            binding.periodTxt.text = image.period
        } else {
            binding.periodTxt.text = "Unknown Date"
        }
        if (image.culture != "") {
            binding.cultureTxt.text = image.culture
        } else {
            binding.cultureTxt.text = "Unknown Culture"

        }

    }


}




