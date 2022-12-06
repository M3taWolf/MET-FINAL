package de.syntaxinstitut.met.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import de.syntaxinstitut.met.MainViewModel
import de.syntaxinstitut.met.adapter.CollectionAdapter
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.databinding.FragmentMyCollectionBinding

class CollectionFragment : Fragment() {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentMyCollectionBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: MainViewModel by activityViewModels()

    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode wenn das View erstellt wird
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCollectionBinding.inflate(inflater)

        return binding.root
    }

    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val openImageFunction: (String) -> Unit= {
            Picasso.get().load(it).into(binding.collectionDetail)
            binding.collectionDetail.visibility = View.VISIBLE
            binding.closeBtn.visibility = View.VISIBLE
        }

        val deleteItem: (Artwork) -> Unit= {
        viewModel.deleteArtwork(it)
        }

        val adapter = CollectionAdapter(listOf(), openImageFunction, deleteItem)
        binding.rvCollection.adapter = adapter

        viewModel.collection.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.closeBtn.setOnClickListener {
            binding.closeBtn.visibility = View.INVISIBLE
            binding.collectionDetail.visibility = View.INVISIBLE
        }



    }
}