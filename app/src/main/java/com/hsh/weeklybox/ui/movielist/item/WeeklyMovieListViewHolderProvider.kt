package com.hsh.weeklybox.ui.movielist.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.hsh.weeklybox.R
import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel
import com.hsh.weeklybox.ui.movielist.item.contents.WeeklyMovieListContentsViewHolder
import com.hsh.weeklybox.ui.movielist.item.footer.WeeklyMovieListFooterViewHolder
import com.hsh.weeklybox.ui.movielist.item.header.WeeklyMovieListHeaderViewHolder
import javax.inject.Inject

class WeeklyMovieListViewHolderProvider @Inject constructor() {

    @Suppress("UNCHECKED_CAST")
    fun getViewHolder(
        parent: ViewGroup,
        viewType: MovieListItemType
    ): WeeklyMovieListItemViewHolder<WeeklyMovieListModel, ViewDataBinding> {
        return when (viewType) {
            MovieListItemType.HEADER -> WeeklyMovieListHeaderViewHolder(
                getItemView(parent, R.layout.item_weekly_movie_list_header)
            )
            MovieListItemType.CONTENTS -> WeeklyMovieListContentsViewHolder (
                getItemView(parent, R.layout.item_weekly_movie_list_contents)
            )
            MovieListItemType.FOOTER -> WeeklyMovieListFooterViewHolder (
                getItemView(parent, R.layout.item_weekly_movie_list_footer)
            )
        } as WeeklyMovieListItemViewHolder<WeeklyMovieListModel, ViewDataBinding>
    }

    private fun getItemView(parent: ViewGroup, @LayoutRes layoutId: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }
}