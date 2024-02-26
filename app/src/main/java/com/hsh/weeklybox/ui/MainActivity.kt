package com.hsh.weeklybox.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hsh.weeklybox.R
import com.hsh.weeklybox.ui.common.helper.FragmentHelper
import com.hsh.weeklybox.ui.movielist.WeeklyMovieDetailFragment
import com.hsh.weeklybox.ui.movielist.WeeklyMovieListFragment
import com.hsh.weeklybox.ui.movielist.WeeklyMovieListSharedViewModel
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieListEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<WeeklyMovieListSharedViewModel>()
    private val fragmentHelper = FragmentHelper(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is WeeklyMovieListEvent.GoToWeeklyMovieDetailInfoScreen -> {
                    fragmentHelper.addFragment(
                        fragment = createWeeklyMovieDetailFragment(event.movieCd),
                        tag = WeeklyMovieDetailFragment::class.qualifiedName
                    )
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun createWeeklyMovieDetailFragment(movieCd: String): WeeklyMovieDetailFragment {
        return WeeklyMovieDetailFragment.newInstance(
            movieCd = movieCd
        )
    }

    private fun initView() {
        fragmentHelper.replaceFragment(WeeklyMovieListFragment.getInstance())
    }
}