package com.mkrlabs.dashboard.ui.dashboard.nav_drawer.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.data.model.response.NotificationItem
import com.mkrlabs.dashboard.databinding.NotificationItemBinding

class NotificationAdapter(private val onItemClicked: (item: NotificationItem) -> Unit) : RecyclerView.Adapter<NotificationAdapter.NotificationListViewHolder>() {

    private val items = arrayListOf<NotificationItem>()

    fun submitList(data: List<NotificationItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListViewHolder {
        val binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.binding.root.setOnClickListener {
            onItemClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size



    class NotificationListViewHolder(val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationItem) {
            binding.notificationTitle.text = item.notification_title
            binding.notificationDescription.text = item.description
        }
    }
}
