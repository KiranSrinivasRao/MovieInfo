package com.ikran.movieinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.ikran.movieinfo.R
import com.ikran.movieinfo.data.SearchItem

class SearchListAdapter :PagedListAdapter<SearchItem, SearchViewHolder>(SearchItem.DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.view_search_item,
            parent,
            false
        ))


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}