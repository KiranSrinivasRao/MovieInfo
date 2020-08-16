package com.ikran.movieinfo.utilities

import com.ikran.movieinfo.R

data class LoadingState(val state: State)

enum class State(val stringRes: Int) {

    LOADING(R.string.loading),
    LOADED(R.string.loaded),
    ERROR(R.string.unknown_error),
    NO_RECORD_FOUND(R.string.no_movie_found);
}