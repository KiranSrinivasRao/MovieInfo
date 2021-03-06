package com.ikran.movieinfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.ikran.movieinfo.R
import com.ikran.movieinfo.activities.SearchType
import com.ikran.movieinfo.adapter.SearchListAdapter
import com.ikran.movieinfo.data.SearchItem
import com.ikran.movieinfo.utilities.hideKeyboard
import com.ikran.movieinfo.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private var searchPagedLiveData:LiveData<PagedList<SearchItem>>? = null
    private lateinit var searchListAdapter:SearchListAdapter

    val searchViewModel: SearchViewModel by viewModels()

    override val screenTitle: String
        get() = getString(R.string.search_page_title)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchListAdapter = SearchListAdapter()

        recyclerView.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = searchListAdapter

        configureSearchView()

        configureSearchTypeSlider()

        searchViewModel.searchStateNotifier.observe(viewLifecycleOwner, Observer {
            showSnackBarWithMessage(it.state.stringRes)
        })
    }

    private fun configureSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTitle()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

    private fun configureSearchTypeSlider() {
        context?.let { ctxt ->
            searchType.adapter = ArrayAdapter(
                ctxt,
                android.R.layout.simple_spinner_dropdown_item,
                android.R.id.text1,
                SearchType.values()
            )
        }
        configureSliderListener()
    }

    fun configureSliderListener() {
        searchType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                searchTitle()
            }
        }
    }

    /**
     * Search the  title
     * Hide the keyboard after searching the title
     */
    fun searchTitle() {
        searchView.hideKeyboard()

        val query = searchView.query
        val titleType =
            (searchType.adapter.getItem(searchType.selectedItemPosition) as? SearchType)?.searchText

        searchPagedLiveData?.removeObservers(this)
        searchPagedLiveData =
            searchViewModel.getSearchItemPagedList(query.toString(), titleType ?: "")
        searchPagedLiveData?.observe(this, Observer {
            searchListAdapter.submitList(it)
        })
    }

}