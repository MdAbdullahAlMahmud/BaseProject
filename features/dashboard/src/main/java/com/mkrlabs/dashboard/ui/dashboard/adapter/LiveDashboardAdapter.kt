package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.databinding.FeatureItemBinding
import com.mkrlabs.dashboard.databinding.LiveDashboardItemBinding

class LiveDashboardAdapter(private val onItemClicked: (item: LiveDashboardItem) -> Unit) : RecyclerView.Adapter<LiveDashboardAdapter.LiveDashboardListViewHolder>(){

    private  val items = arrayListOf<LiveDashboardItem>()

    fun submitList (data : List<LiveDashboardItem>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveDashboardListViewHolder {
        val binding = LiveDashboardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  LiveDashboardListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LiveDashboardListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,holder.itemView.context)

        holder.featureItemBinding.featureRoot.setOnClickListener{
            onItemClicked(item)
        }

    }




    class  LiveDashboardListViewHolder(val  featureItemBinding : LiveDashboardItemBinding) : RecyclerView.ViewHolder(featureItemBinding.root){
        fun bind(item: LiveDashboardItem, context: Context){
            featureItemBinding.tvFeatureName.text = item.name
        }
    }


}