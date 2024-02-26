package com.hsh.weeklybox.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hsh.weeklybox.R
import com.hsh.weeklybox.databinding.FragmentWeeklyMovieDetailBinding
import com.hsh.weeklybox.ui.common.const.ExtraKey
import com.hsh.weeklybox.ui.common.helper.ProgressDialogHelper
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieDetailEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyMovieDetailFragment : Fragment() {
    private var _binding: FragmentWeeklyMovieDetailBinding? = null
    private val viewModel by viewModels<WeeklyMovieDetailViewModel>()
    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.onCloseBtnClick()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    val binding: FragmentWeeklyMovieDetailBinding?
        get() = _binding

    override fun onDestroy() {
        callback.remove()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_movie_detail, container, false)
        _binding?.vm = viewModel

        //init
        init()

        //init observer
        initObserver()

        return _binding?.root
    }

    private fun init() {
        viewModel.getWeeklyMovieList(getString(R.string.movie_api_key))
    }

    private fun showProgress(onProgress: Boolean) {
        if (onProgress) {
            ProgressDialogHelper.show(requireContext())
        } else {
            ProgressDialogHelper.dismiss()
        }
    }

    private fun initObserver() {
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            showProgress(isLoading)
        }

        viewModel.event.observe(viewLifecycleOwner) { entity ->
            when (entity) {
                is WeeklyMovieDetailEvent.CloseMovieDetailScreen -> {
                    requireActivity().supportFragmentManager.popBackStack()
                }
                else -> {
                    // Nothing to do
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(movieCd: String): WeeklyMovieDetailFragment {
            val args = Bundle().apply {
                putString(ExtraKey.MOVIE_CODE, movieCd)
            }
            return WeeklyMovieDetailFragment().apply {
                arguments = args
            }
        }
    }
}