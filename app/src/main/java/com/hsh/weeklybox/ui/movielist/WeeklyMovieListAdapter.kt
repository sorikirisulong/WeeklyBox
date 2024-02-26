package com.hsh.weeklybox.ui.movielist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hsh.weeklybox.framework.extension.orZero
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewType
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewHolder
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListViewHolderProvider

class WeeklyMovieListAdapter :
    RecyclerView.Adapter<WeeklyMovieListItemViewHolder<WeeklyMovieListModel, ViewDataBinding>>() {

    private val list = mutableListOf<WeeklyMovieListModel>()
    private val holderProvider = WeeklyMovieListViewHolderProvider()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeeklyMovieListItemViewHolder<WeeklyMovieListModel, ViewDataBinding> {
        return holderProvider.getViewHolder(parent, WeeklyMovieListItemViewType.values()[viewType])
    }

    override fun onBindViewHolder(
        holder: WeeklyMovieListItemViewHolder<WeeklyMovieListModel, ViewDataBinding>,
        position: Int
    ) {
        list.getOrNull(position)?.let { model ->
            holder.onBind(model, position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(items: List<WeeklyMovieListModel>) {
        if (items.isEmpty()) return
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return list.getOrNull(position)?.getViewType()?.ordinal.orZero()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}