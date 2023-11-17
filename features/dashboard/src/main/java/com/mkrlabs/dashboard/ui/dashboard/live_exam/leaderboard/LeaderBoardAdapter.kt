package com.mkrlabs.dashboard.ui.dashboard.live_exam.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.data.model.response.LeaderBoardItem
import com.mkrlabs.dashboard.databinding.LeaderBoardItemBinding

class LeaderBoardAdapter (private val onItemClicked: (item: LeaderBoardItem) -> Unit) : RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardListViewHolder>() {

    private val items = arrayListOf<LeaderBoardItem>()

    fun submitList(data: List<LeaderBoardItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardListViewHolder {
        val binding = LeaderBoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaderBoardListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LeaderBoardListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,position)

        holder.binding.root.setOnClickListener {
            onItemClicked(item)
        }

    }


    class LeaderBoardListViewHolder(val binding: LeaderBoardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LeaderBoardItem,position: Int) {
            binding.leaderUserName.text = "User name"
            binding.leaderRankTv.text ="${ position +4}"
            binding.leaderTimeTv.text = item.time
            binding.leaderScoreTv.text = "${item.score}/100"

        }
    }
}
