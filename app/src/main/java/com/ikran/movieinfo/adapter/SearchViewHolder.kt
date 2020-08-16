package com.ikran.movieinfo.adapter

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ikran.movieinfo.R
import com.ikran.movieinfo.data.SearchItem
import com.ikran.movieinfo.databinding.ViewSearchItemBinding
import com.ikran.movieinfo.fragments.DetailFragment

class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(item : SearchItem){
        DataBindingUtil.bind<ViewSearchItemBinding>(itemView)?.searchItem = item

        itemView.setOnClickListener {
            itemView.findNavController().navigate(
                R.id.detailFragment,
                bundleOf(DetailFragment.ARG_TITLE_ID to item.id)
            )
        }
    }
}