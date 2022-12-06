package de.syntaxinstitut.met.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.met.MainViewModel
import de.syntaxinstitut.met.R
import de.syntaxinstitut.met.adapter.ExhibitionAdapter
import de.syntaxinstitut.met.data.model.Datasource
import de.syntaxinstitut.met.databinding.FragmentExhibitionDetailBinding


class ExhibitionDetailFragment : Fragment() {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentExhibitionDetailBinding

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
        binding = FragmentExhibitionDetailBinding.inflate(inflater)

        return binding.root
    }

    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exhibitionId: Int = requireArguments().getInt("exhibition_id")
        val exhibition = viewModel.loadExhibitionById(exhibitionId)


        if (exhibition != null) {
            binding.exDetail.text = exhibition.exhibitionDetail
            binding.exName.text = exhibition.exhibitionTxt
            binding.exDetail1.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    exhibition.exhibitionImg1
                )
            )
            binding.exDetail2.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    exhibition.exhibitionImg2
                )
            )
            binding.exDetail3.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    exhibition.exhibitionImg3
                )
            )
            binding.exImgMain.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    exhibition.imageResource
                )
            )
        }


    }
}






