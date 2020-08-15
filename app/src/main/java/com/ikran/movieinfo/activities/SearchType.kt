package com.ikran.movieinfo.activities

enum class SearchType( val searchText: String) {
    ALL (""),
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode")
}