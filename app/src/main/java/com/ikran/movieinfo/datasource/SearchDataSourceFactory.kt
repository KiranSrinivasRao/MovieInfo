package com.ikran.movieinfo.datasource

import androidx.paging.DataSource
import com.ikran.movieinfo.data.SearchItem
import com.ikran.movieinfo.viewmodel.SearchViewModel

class SearchDataSourceFactory (@JvmSuppressWildcards private val searchViewModel: SearchViewModel) : DataSource.Factory<Int, SearchItem>(){
    override fun create(): DataSource<Int, SearchItem> =
        SearchDataSource(searchViewModel)

}