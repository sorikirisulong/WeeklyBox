package com.hsh.weeklybox.ui.movielist.item

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel

abstract class WeeklyMovieListItemViewHolder<VM : WeeklyMovieListModel, B : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val binding: B? = DataBindingUtil.bind(itemView)

    abstract fun onBind(model: VM, position: Int)
}