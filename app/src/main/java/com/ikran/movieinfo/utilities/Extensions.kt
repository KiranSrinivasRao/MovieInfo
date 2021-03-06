package com.ikran.movieinfo.utilities

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.ContentLoadingProgressBar
import com.ikran.movieinfo.viewmodel.BaseViewModel
import io.reactivex.disposables.Disposable

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun <T : BaseViewModel> Disposable.addToDisposableBucket(viewModel: T) {
    viewModel.compositeDisposable.add(this)
}

fun ContentLoadingProgressBar.start() {
    visibility = View.VISIBLE
    show()
}

fun ContentLoadingProgressBar.stop() {
    visibility = View.GONE
    hide()
}