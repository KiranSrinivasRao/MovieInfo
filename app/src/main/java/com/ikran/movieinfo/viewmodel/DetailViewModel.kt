package com.ikran.movieinfo.viewmodel

import addToDisposableBucket
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ikran.movieinfo.data.TitleDetail
import com.ikran.movieinfo.network.MovieApi
import io.reactivex.android.schedulers.AndroidSchedulers


class DetailViewModel @ViewModelInject constructor(private val movieApi: MovieApi) :BaseViewModel(){

    private val detailLiveData = MutableLiveData<TitleDetail?>()

    fun getTitleDetail(titleId: String): LiveData<TitleDetail?> {
        movieApi.getTitle(titleId).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailLiveData.value = it
            }, {
                detailLiveData.value = null
            }).addToDisposableBucket(this)
        return detailLiveData
    }

}