package com.hsh.weeklybox.ui.movielist.item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsh.weeklybox.databinding.SimpleItemWeeklyMoveListContentsBinding
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyBoxOfficeListItemApdater : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<WeeklyBoxOfficeUIModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = SimpleItemWeeklyMoveListContentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeeklyBoxOfficeViewHolder(itemBinding)
    }

    private fun isSafeIndex(collection: Collection<*>, index: Int): Boolean {
        return if (collection.isEmpty()) {
            false
        } else index >= 0 && collection.size > index
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isSafeIndex(list, position).not()) return
        when (holder) {
            is WeeklyBoxOfficeViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(newList: List<WeeklyBoxOfficeUIModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class WeeklyBoxOfficeViewHolder(val binding: SimpleItemWeeklyMoveListContentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeeklyBoxOfficeUIModel) {
            binding.model = data
        }
    }
}