package com.ikran.movieinfo.network

import com.ikran.movieinfo.data.SearchResult
import com.ikran.movieinfo.data.TitleDetail
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    private companion object{
        const val API_KEY = "b9bd48a6"
    }


    @GET("/?apikey=$API_KEY")
    fun searchTitle(
        @Query("s") search:String,
        @Query("type") type:String,
        @Query("page") pageNum: Int = 1
    ):Call<SearchResult>


    @GET("/?apikey=$API_KEY")
    fun getTitle(@Query("i") id: String) :Single<TitleDetail>
}