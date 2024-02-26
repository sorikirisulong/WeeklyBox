package com.hsh.weeklybox.ui.movielist.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.hsh.weeklybox.BR

class WeeklyMovieListViewState : BaseObservable() {

    @get:Bindable
    var hasData = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.hasData)
        }

    @get:Bindable
    var textFailOverView = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textFailOverView)
        }
}
