package com.ikran.movieinfo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ikran.movieinfo.R
import com.ikran.movieinfo.data.TitleDetail
import com.ikran.movieinfo.databinding.FragmentDetailBinding
import com.ikran.movieinfo.utilities.State
import com.ikran.movieinfo.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    companion object {
        const val ARG_TITLE_ID = "ARG_TITLE_ID"
    }

    override val screenTitle: String
        get() = getString(R.string.detail_string)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  = inflater.inflate(R.layout.fragment_detail, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_TITLE_ID)?.let { titleId ->
            val viewModel: DetailViewModel by viewModels()
            showLoading()
            viewModel.getTitleDetail(titleId).observe(viewLifecycleOwner, Observer { titleDetail ->
                if (titleDetail == null) {
                    showSnackBarWithMessage(State.ERROR.stringRes)
                } else {
                    setData(titleDetail)
                }
            })
        }
    }

    private fun setData(titleDetail: TitleDetail) {
        view?.let { rootView ->
            DataBindingUtil.bind<FragmentDetailBinding>(rootView)?.titleDetail = titleDetail
        }
        hideLoading()
    }
}