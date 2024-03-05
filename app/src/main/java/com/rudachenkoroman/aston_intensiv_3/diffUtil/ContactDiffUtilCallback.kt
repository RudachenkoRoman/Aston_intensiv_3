package com.rudachenkoroman.aston_intensiv_3.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.rudachenkoroman.aston_intensiv_3.model.Contact

class ContactDiffUtilCallback(private val oldList: List<Contact>,
                              private val newList: List<Contact>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
