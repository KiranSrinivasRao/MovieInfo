package com.ikran.movieinfo.viewmodel

import androidx.annotation.WorkerThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ikran.movieinfo.data.SearchItem
import com.ikran.movieinfo.data.SearchResult
import com.ikran.movieinfo.datasource.SearchDataSourceFactory
import com.ikran.movieinfo.network.MovieApi
import com.ikran.movieinfo.utilities.LoadingState
import com.ikran.movieinfo.utilities.State


class SearchViewModel @ViewModelInject constructor(private val movieApi:MovieApi): BaseViewModel() {

    private var itemPagedList: LiveData<PagedList<SearchItem>>? = null
    private var persistedQuery: String? = null
    private var persistedType: String? = null

    // Backing Field
    private val _searchStateNotifier = MutableLiveData<LoadingState>()

    val searchStateNotifier
        get() = _searchStateNotifier

    fun getSearchItemPagedList(query: String, titleType: String): LiveData<PagedList<SearchItem>>? {
        if (shouldIgnoreNewSearch(query, titleType)) return itemPagedList

        persistedQuery = query
        persistedType = titleType

        val dataSourceFactory = SearchDataSourceFactory(this)
        val pagedListConfig =
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(1).build()
        itemPagedList = LivePagedListBuilder(
            dataSourceFactory,
            pagedListConfig
        ).build()
        return itemPagedList!!
    }

    private fun shouldIgnoreNewSearch(query: String, titleType: String) =
        (query == persistedQuery && persistedType == titleType) || query.isEmpty()

    @WorkerThread
    fun searchTitle(pageNo: Int = 1, onNewSearchResult: (SearchResult) -> Unit) {
        searchStateNotifier.postValue(LoadingState(State.LOADING))
        val searchResponse = movieApi.searchTitle(persistedQuery!!, persistedType!!, pageNo)
            .execute().body()
        when {
            searchResponse == null -> searchStateNotifier.postValue(LoadingState(State.ERROR))
            !searchResponse.response -> searchStateNotifier.postValue(LoadingState(State.NO_RECORD_FOUND))
            searchResponse.response -> {
                onNewSearchResult(searchResponse)
                searchStateNotifier.postValue(LoadingState(State.LOADED))
            }
        }
    }
}