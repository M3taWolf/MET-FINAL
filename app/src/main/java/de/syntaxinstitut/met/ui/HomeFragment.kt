package de.syntaxinstitut.met.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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
import de.syntaxinstitut.met.databinding.FragmentHomeBinding

/**
 * Fragment 1
 */
class HomeFragment : Fragment() {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentHomeBinding

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
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fader()
        binding.exploreBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionOneFragmentToTwoFragment())
        }
      val exhibitionRecyclerView: RecyclerView = view.findViewById(R.id.ex_recycler)
        val exhibitionAdapter = ExhibitionAdapter(dataset = Datasource().loadExhibitions())
        exhibitionRecyclerView.adapter = exhibitionAdapter


        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(exhibitionRecyclerView)



        val collectionConstraintLayout: ConstraintLayout = view.findViewById(R.id.cl_item_my_collection)
        collectionConstraintLayout.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionOneFragmentToCollectionFragment())
        }




        /**
         * Implizierter Intent für Google Maps
         */

       view.findViewById<CardView>(R.id.cardview_location1).setOnClickListener {

           // Create a Uri from an intent string. Use the result to create an Intent.

           var gmmIntentUri = Uri.parse("geo:0,0?q=The Met Fifth Avenue")
           if (false) {
                gmmIntentUri = Uri.parse("geo:0,0?q=The Met Cloisters")
           }






// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
           val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
// Make the Intent explicit by setting the Google Maps package
           mapIntent.setPackage("com.google.android.apps.maps")

// Attempt to start an activity that can handle the Intent
           startActivity(mapIntent)

        }



    }
    //private fun fader() {

       // binding.exploreBtn.alpha = 0f
        //var toAlpha = 1f

        //val animator = ObjectAnimator.ofFloat(binding.exploreBtn, View.ALPHA, toAlpha)
        //animator.duration = 200
        //animator.start()


    }


