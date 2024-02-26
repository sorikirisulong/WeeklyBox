package com.hsh.weeklybox.ui.movielist.item.footer

import android.view.View
import com.hsh.weeklybox.databinding.ItemWeeklyMovieListFooterBinding
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewHolder

class WeeklyMovieListFooterViewHolder(
    itemView: View
) : WeeklyMovieListItemViewHolder<WeeklyMovieListFooterModel, ItemWeeklyMovieListFooterBinding>(itemView) {

    override fun onBind(model: WeeklyMovieListFooterModel, position: Int) {
        binding?.model = model
    }
}