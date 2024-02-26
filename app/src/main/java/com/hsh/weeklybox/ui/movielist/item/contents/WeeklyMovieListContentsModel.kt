package com.hsh.weeklybox.ui.movielist.item.contents

import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyMovieListContentsModel(
    val hasWeeklyBoxOfficeList: Boolean,
    val weeklyBoxOfficeList: List<WeeklyBoxOfficeUIModel>,
) : WeeklyMovieListModel