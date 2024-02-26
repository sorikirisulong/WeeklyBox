package com.hsh.weeklybox.ui.movielist.item.contents

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hsh.weeklybox.data.model.weeklymovielist.WeeklyBoxOffice
import com.hsh.weeklybox.databinding.ItemWeeklyMovieListContentsBinding
import com.hsh.weeklybox.ui.movielist.item.WeeklyBoxOfficeListItemApdater
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewHolder
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyMovieListContentsViewHolder(
    itemView: View
) : WeeklyMovieListItemViewHolder<WeeklyMovieListContentsModel, ItemWeeklyMovieListContentsBinding>(
    itemView
) {
    private val weeklyBoxOfficeListItemAdapter = WeeklyBoxOfficeListItemApdater()

    init {
        binding?.recyclerviewFacility?.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
//            addItemDecoration(
//                GridInsetDecoration(DisplayUtils.dp2px(8f), DisplayUtils.dp2px(20f))
//            )
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = weeklyBoxOfficeListItemAdapter
        }
    }

    override fun onBind(model: WeeklyMovieListContentsModel, position: Int) {
        binding?.model = model
        setItemList(model.weeklyBoxOfficeList)
        binding?.executePendingBindings()
    }

    private fun setItemList(list: List<WeeklyBoxOfficeUIModel>?) {
        weeklyBoxOfficeListItemAdapter.setItemList(list ?: emptyList())
    }
}