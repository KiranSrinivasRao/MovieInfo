package com.ikran.movieinfo.datasource

import androidx.paging.PageKeyedDataSource
import com.ikran.movieinfo.data.SearchItem
import com.ikran.movieinfo.viewmodel.SearchViewModel

class SearchDataSource (@JvmSuppressWildcards private val searchViewModel: SearchViewModel):PageKeyedDataSource<Int, SearchItem>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, SearchItem>
    ) {
        searchViewModel.searchTitle {
            callback.onResult(it.searchItems ?: emptyList(),null,2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItem>) {
        searchViewModel.searchTitle( pageNo = params.key) {
            callback.onResult(it.searchItems ?: emptyList(), params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItem>) = Unit

}