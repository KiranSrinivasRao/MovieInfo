package com.ikran.movieinfo.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class Rating(

    @JsonProperty("Source") val source: String,
    @JsonProperty("Value") val value: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TitleDetail(

    @JsonProperty("Title") val moviename: String?,
    @JsonProperty("Year") val year: String?,
    @JsonProperty("Rated") val rated: String?,
    @JsonProperty("Released") val released: String?,
    @JsonProperty("Runtime") val runtime: String?,
    @JsonProperty("Genre") val genre: String?,
    @JsonProperty("Director") val director: String?,
    @JsonProperty("Writer") val writer: String?,
    @JsonProperty("Actors") val actors: String?,
    @JsonProperty("Plot") val plot: String?,
    @JsonProperty("Language") val language: String?,
    @JsonProperty("Country") val country: String?,
    @JsonProperty("Awards") val awards: String?,
    @JsonProperty("Poster") val poster: String?,
    @JsonProperty("Ratings") val ratings: List<Rating?>?,
    @JsonProperty("Metascore") val metascore: String?,
    @JsonProperty("imdbRating") val imdbRating: String?,
    @JsonProperty("imdbVotes") val votes: String?,
    @JsonProperty("imdbID") val id: String?,
    @JsonProperty("Type") val type: String?,
    @JsonProperty("DVD") val dvd: String?,
    @JsonProperty("BoxOffice") val boxoffice: String?,
    @JsonProperty("Production") val production: String?,
    @JsonProperty("Website") val website: String?,
    @JsonProperty("Response") val response: Boolean,
    @JsonProperty("Error") val error: String?
)
