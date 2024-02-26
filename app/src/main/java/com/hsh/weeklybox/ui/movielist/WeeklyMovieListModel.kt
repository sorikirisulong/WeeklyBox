package com.hsh.weeklybox.ui.movielist

import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewType
import com.hsh.weeklybox.ui.movielist.item.contents.WeeklyMovieListContentsModel
import com.hsh.weeklybox.ui.movielist.item.footer.WeeklyMovieListFooterModel
import com.hsh.weeklybox.ui.movielist.item.header.WeeklyMoviewListHeaderModel

interface WeeklyMovieListModel {

    fun getViewType(): WeeklyMovieListItemViewType {
        return when (this) {
            is WeeklyMoviewListHeaderModel -> WeeklyMovieListItemViewType.HEADER
            is WeeklyMovieListContentsModel -> WeeklyMovieListItemViewType.CONTENTS
            is WeeklyMovieListFooterModel -> WeeklyMovieListItemViewType.FOOTER
            else -> WeeklyMovieListItemViewType.HEADER
        }
    }
}