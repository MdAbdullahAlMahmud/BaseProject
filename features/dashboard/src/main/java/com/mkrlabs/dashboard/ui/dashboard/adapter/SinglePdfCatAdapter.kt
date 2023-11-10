package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.databinding.SubTopicItemBinding

class SinglePdfCatAdapter (private val onItemClicked: (item: SinglePdfCatItem) -> Unit) : RecyclerView.Adapter<SinglePdfCatAdapter.SinglePdfCatListViewHolder>() {

    private val items = arrayListOf<SinglePdfCatItem>()

    fun submitList(data: List<SinglePdfCatItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePdfCatListViewHolder {
        val binding = SubTopicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SinglePdfCatListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SinglePdfCatListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.subTopicItemBinding.topicRoot.setOnClickListener {
            onItemClicked(item)
        }

    }


    class SinglePdfCatListViewHolder(val subTopicItemBinding: SubTopicItemBinding) :
        RecyclerView.ViewHolder(subTopicItemBinding.root) {
        fun bind(item: SinglePdfCatItem) {
            subTopicItemBinding.subTopicView.visibility = View.VISIBLE
            subTopicItemBinding.tvTopicName.text = item.menu_name
        }
    }
}