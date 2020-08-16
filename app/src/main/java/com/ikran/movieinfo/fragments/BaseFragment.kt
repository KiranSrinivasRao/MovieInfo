package com.ikran.movieinfo.fragments

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ikran.movieinfo.R
import com.ikran.movieinfo.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment : Fragment() {

    abstract val screenTitle: String

    override fun onStart() {
        super.onStart()
        activity?.title = screenTitle
    }

    fun showLoading() = (activity as? BaseActivity)?.showLoading()

    fun hideLoading() = (activity as? BaseActivity)?.hideLoading()

    private fun showSnackBarWithMessage(message: String) {
        view?.run {

                Snackbar
                    .make(this, message, Snackbar.LENGTH_SHORT).show()

        }
    }

    fun showSnackBarWithMessage(@StringRes stringRes: Int) {
        showSnackBarWithMessage(getString(stringRes))
    }
}