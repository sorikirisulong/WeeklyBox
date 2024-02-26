package com.hsh.weeklybox.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hsh.weeklybox.R
import com.hsh.weeklybox.databinding.FragmentWeeklyMovieListBinding
import com.hsh.weeklybox.ui.common.helper.ProgressDialogHelper
import com.hsh.weeklybox.ui.movielist.event.WeeklyMovieListClickEvent
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieListEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklyMovieListFragment : Fragment() {
    private var _binding: FragmentWeeklyMovieListBinding? = null
    private val viewModel by viewModels<WeeklyMovieListViewModel>()
    private val sharedViewModel by activityViewModels<WeeklyMovieListSharedViewModel>()
    private val weeklyMovieListAdapter = WeeklyMovieListAdapter()

    val binding: FragmentWeeklyMovieListBinding?
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_movie_list, container, false)
        _binding?.vm = viewModel

        initRecyclerView()
//        initData()
        initObserver()
        return _binding?.root
    }

    private fun searchWeeklyMovieList() {
        viewModel.getWeeklyMovieList(
            getString(R.string.movie_api_key),
            binding?.searchView?.text.toString()
        )
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
                is WeeklyMovieListEvent.Items -> weeklyMovieListAdapter.setItemList(entity.items)
                is WeeklyMovieListEvent.SearchButtonClick -> {
                    searchWeeklyMovieList()
                }

                else -> {
                    // Nothing to do
                }
            }
        }

        viewModel.clickEvent.observe(viewLifecycleOwner) {
            when (it) {
                is WeeklyMovieListClickEvent.GoToDetailWeeklyMovieInfo -> {
                    sharedViewModel.goToWeeklyMovieDetailInfoScreen(it.movieCd)
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding?.recyclerview) {
            this?.adapter = weeklyMovieListAdapter
            this?.layoutManager = LinearLayoutManager(requireContext())
            this?.itemAnimator = null
            this?.setHasFixedSize(true)
        }
    }

    companion object {
        fun getInstance(): WeeklyMovieListFragment {
            return WeeklyMovieListFragment()
        }
    }
}