package com.hsh.weeklybox.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
import kotlin.system.exitProcess

@AndroidEntryPoint
class WeeklyMovieListFragment : Fragment() {
    private var _binding: FragmentWeeklyMovieListBinding? = null
    private val viewModel by viewModels<WeeklyMovieListViewModel>()
    private val sharedViewModel by activityViewModels<WeeklyMovieListSharedViewModel>()
    private val weeklyMovieListAdapter = WeeklyMovieListAdapter()
    private lateinit var callback: OnBackPressedCallback

    val binding: FragmentWeeklyMovieListBinding?
        get() = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.onBackButtonClick()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weekly_movie_list, container, false)
        _binding?.vm = viewModel

        initRecyclerView()
        observeBackPressEvent()
        initObserver()
        return _binding?.root
    }


    private fun showExitToast() {
        Toast.makeText(requireContext(), getString(R.string.title_exit_warning), Toast.LENGTH_SHORT).show()
    }

    private fun observeBackPressEvent() {
        viewModel.observeBackPressEvent()
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

    fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding?.searchView?.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(binding?.searchView?.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun submitList(items: List<WeeklyMovieListModel>) {
        weeklyMovieListAdapter.setItemList(items)
    }

    private fun initObserver() {
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            showProgress(isLoading)
        }

        viewModel.event.observe(viewLifecycleOwner) { entity ->
            when (entity) {
                is WeeklyMovieListEvent.Items -> {
                    submitList(entity.items)
                }

                is WeeklyMovieListEvent.SearchButtonClick -> {
                    hideKeyboard()
                    searchWeeklyMovieList()
                }

                is WeeklyMovieListEvent.ShowExitWarningToast -> {
                    showExitToast()
                }

                is WeeklyMovieListEvent.CloseWeeklyMovieListScreen -> {
                    requireActivity().finishAffinity()
                    exitProcess(0)
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

    override fun onDestroy() {
        callback.remove()
        super.onDestroy()
    }

    companion object {
        fun getInstance(): WeeklyMovieListFragment {
            return WeeklyMovieListFragment()
        }
    }
}