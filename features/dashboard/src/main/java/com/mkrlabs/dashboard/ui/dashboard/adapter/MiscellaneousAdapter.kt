package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.databinding.MiscellaneousItemBinding

class MiscellaneousAdapter ( private val  onItemClicked : (item : FeatureItem) -> Unit ) : RecyclerView.Adapter<MiscellaneousAdapter.MiscellaneousViewHolder>(){


    private val items = arrayListOf<FeatureItem>()
    fun submitList(list : List<FeatureItem>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiscellaneousViewHolder {
        val binding = MiscellaneousItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MiscellaneousViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MiscellaneousViewHolder, position: Int) {
        val item  = items[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onItemClicked(item)
        }
    }

    override fun getItemCount(): Int  = items.size

    class  MiscellaneousViewHolder (val  binding : MiscellaneousItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FeatureItem) {
            binding.featureTitle.text = item.featureName
            item.drawable?.let {
                binding.featureImage.setImageResource(it)
            } ?: kotlin.run {
                Glide
                    .with(binding.root.context)
                    .load(item.iconUrl?.toUri())
                    .into(binding.featureImage)
            }
        }
    }
}