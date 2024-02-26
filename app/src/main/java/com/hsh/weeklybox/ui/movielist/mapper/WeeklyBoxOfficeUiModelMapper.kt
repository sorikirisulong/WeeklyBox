package com.hsh.weeklybox.ui.movielist.mapper

import com.hsh.weeklybox.data.model.weeklymovielist.WeeklyBoxOffice
import com.hsh.weeklybox.ui.common.handler.ActionHandle
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyBoxOfficeUiModelMapper  {
    fun mapToItemModel(weeklyBoxOffice: WeeklyBoxOffice?, eventHandler: ActionHandle): WeeklyBoxOfficeUIModel {
        return WeeklyBoxOfficeUIModel(
            weeklyBoxOffice = weeklyBoxOffice!!,
            eventHandler = eventHandler
        )
    }
}