package com.ikran.movieinfo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ikran.movieinfo.data.TitleDetail
import com.ikran.movieinfo.network.MovieApi
import com.ikran.movieinfo.utilities.addToDisposableBucket
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io


class DetailViewModel @ViewModelInject constructor(private val movieApi: MovieApi) :BaseViewModel(){

    private val detailLiveData = MutableLiveData<TitleDetail?>()

    fun getTitleDetail(titleId: String): LiveData<TitleDetail?> {
        movieApi.getTitle(titleId)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailLiveData.value = it
            }, {
                detailLiveData.value = null
            }).addToDisposableBucket(this)
        return detailLiveData
    }

}