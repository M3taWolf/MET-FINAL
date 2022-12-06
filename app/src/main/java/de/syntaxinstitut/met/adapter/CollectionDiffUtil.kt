package de.syntaxinstitut.met.adapter

import androidx.recyclerview.widget.DiffUtil
import de.syntaxinstitut.met.data.datamodels.Artwork

class CollectionDiffUtil(private val oldList: List<Artwork>, private val newList: List<Artwork>): DiffUtil.Callback(){

    override fun getOldListSize(): Int {
         return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].objectID == newList[newItemPosition].objectID
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}