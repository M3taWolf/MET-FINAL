package de.syntaxinstitut.met.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.met.R
import de.syntaxinstitut.met.data.model.Exhibition
import de.syntaxinstitut.met.ui.HomeFragmentDirections

class ExhibitionAdapter(var dataset: List<Exhibition>) : RecyclerView.Adapter<ExhibitionAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.exhibition_img)
        val exhibitionTxt: TextView = view.findViewById(R.id.exhibition_txt)
        val exhibitionDate: TextView = view.findViewById(R.id.exhibition_date)
        val whichMuseum: TextView = view.findViewById(R.id.which_museum)
        val exCardView: CardView = view.findViewById(R.id.ex_card_view)

    }

    fun submitList(list: List<Exhibition>) {
        dataset = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // das itemLayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_exhibition, parent, false)

        // und in einem ViewHolder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Exhibition = dataset[position]
        holder.image.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, item.imageResource))
        holder.exhibitionTxt.text = item.exhibitionTxt
        holder.exhibitionDate.text = item.exhibitionDate
        holder.whichMuseum.text = item.whichMuseum
        holder.exCardView.setOnClickListener{
            holder.itemView.findNavController().navigate(HomeFragmentDirections.actionOneFragmentToExhibitionDetailFragment(item.id))
        }


    }



    override fun getItemCount(): Int {
        return dataset.size
    }
}

