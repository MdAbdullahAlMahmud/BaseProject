package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.databinding.SubTopicItemBinding

class SubTopicAdapter (private val onItemClicked: (item: SubTopicItem) -> Unit) : RecyclerView.Adapter<SubTopicAdapter.SubTopicListViewHolder>() {

    private val items = arrayListOf<SubTopicItem>()

    fun submitList(data: List<SubTopicItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicListViewHolder {
        val binding = SubTopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubTopicListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubTopicListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.subTopicItemBinding.topicRoot.setOnClickListener {
            onItemClicked(item)
        }

    }


    class SubTopicListViewHolder(val subTopicItemBinding: SubTopicItemBinding) :
        RecyclerView.ViewHolder(subTopicItemBinding.root) {
        fun bind(item: SubTopicItem) {
            subTopicItemBinding.tvTopicName.text = item.category_name
        }
    }
}