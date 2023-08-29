package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.databinding.TopicItemBinding

class TopicAdapter (private val onItemClicked: (item: TopicItem) -> Unit) : RecyclerView.Adapter<TopicAdapter.TopicListViewHolder>() {

    private val items = arrayListOf<TopicItem>()

    fun submitList(data: List<TopicItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicListViewHolder {
        val binding = TopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TopicListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.topicItemBinding.topicRoot.setOnClickListener {
            onItemClicked(item)
        }

    }


    class TopicListViewHolder(val topicItemBinding: TopicItemBinding) :
        RecyclerView.ViewHolder(topicItemBinding.root) {
        fun bind(item: TopicItem) {
            topicItemBinding.tvTopicName.text = item.category_name
        }
    }
}


