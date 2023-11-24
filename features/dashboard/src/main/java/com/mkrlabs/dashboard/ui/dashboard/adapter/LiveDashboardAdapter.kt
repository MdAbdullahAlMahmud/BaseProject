package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.databinding.FeatureItemBinding
import com.mkrlabs.dashboard.databinding.LiveDashboardItemBinding
import kotlin.random.Random

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
        holder.featureItemBinding.tvFeatureName.setOnClickListener{
            onItemClicked(item)
        }




    }




    class  LiveDashboardListViewHolder(val  featureItemBinding : LiveDashboardItemBinding) : RecyclerView.ViewHolder(featureItemBinding.root){
        fun bind(item: LiveDashboardItem, context: Context){
            featureItemBinding.tvFeatureName.text = item.name
            val color = getRandomColor()
            featureItemBinding.root.setCardBackgroundColor(color)
            featureItemBinding.tvFeatureName.setTextColor(setOppositeTextColor(color))
        }
        fun setOppositeTextColor(backgroundHexColor: Int): Int {
            //val backgroundColor = Color.parseColor(backgroundHexColor)
            val backgroundColor = backgroundHexColor

            // Calculate the relative luminance of the background color
            val luminance = (0.299 * Color.red(backgroundColor) +
                    0.587 * Color.green(backgroundColor) +
                    0.114 * Color.blue(backgroundColor)) / 255.0

            // Decide the text color based on the luminance
            return if (luminance > 0.5) {
                Color.BLACK  // Use black for light backgrounds
            } else {
                Color.WHITE  // Use white for dark backgrounds
            }
        }
        fun getRandomColor(): Int {
            val random = Random
            val red = random.nextInt(256)
            val green = random.nextInt(256)
            val blue = random.nextInt(256)

            return (0xFF shl 24) or (red shl 16) or (green shl 8) or blue
        }
    }




}