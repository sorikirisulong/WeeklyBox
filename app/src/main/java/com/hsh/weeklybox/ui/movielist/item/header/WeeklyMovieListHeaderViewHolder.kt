package com.hsh.weeklybox.ui.movielist.item.header

import android.view.View
import com.hsh.weeklybox.databinding.ItemWeeklyMovieListHeaderBinding
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewHolder

class WeeklyMovieListHeaderViewHolder(
    itemView: View
) : WeeklyMovieListItemViewHolder<WeeklyMoviewListHeaderModel, ItemWeeklyMovieListHeaderBinding>(itemView) {

    override fun onBind(model: WeeklyMoviewListHeaderModel, position: Int) {
        binding?.model = model
    }
}