package de.syntaxinstitut.met.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.syntaxinstitut.met.R
import de.syntaxinstitut.met.data.datamodels.Artwork
import de.syntaxinstitut.met.ui.HomeFragmentDirections

class CollectionAdapter(var dataset: List<Artwork>, val openImageFunction: (String) -> Unit, val deleteArtworkFunction: (Artwork) -> Unit) : RecyclerView.Adapter<CollectionAdapter.ItemViewHolder>() {

    var oldData = dataset.toList()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.my_collection_img)
        val deleteButton: ImageView = view.findViewById(R.id.delete_btn)
    }

    fun submitList(list: List<Artwork>) {
        dataset = list
        CollectionDiffUtil(oldData,dataset).also {
            DiffUtil.calculateDiff(it, false).dispatchUpdatesTo(this)
        }
        oldData = dataset.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // das itemLayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_my_collection, parent, false)

        // und in einem ViewHolder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Artwork = dataset[position]
        Picasso.get().load(item.primaryImage).into(holder.image)
        holder.image.setOnClickListener {
            openImageFunction(item.primaryImage)
        }

        holder.deleteButton.setOnClickListener {
        deleteArtworkFunction(item)
        }



    //TODO Bilder offline speichern und laden
        }

    override fun getItemCount(): Int {
        return dataset.size
    }


}



