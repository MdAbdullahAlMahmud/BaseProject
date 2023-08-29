package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.databinding.FeatureItemBinding

class FeatureListAdapter(private val onItemClicked: (item: FeatureItem) -> Unit) : RecyclerView.Adapter<FeatureListAdapter.FeatureListViewHolder>(){

    private  val items = arrayListOf<FeatureItem>()

    fun submitList (data : List<FeatureItem>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureListViewHolder {
       val binding = FeatureItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  FeatureListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FeatureListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,holder.itemView.context)

        holder.featureItemBinding.featureRoot.setOnClickListener{
            onItemClicked(item)
        }

    }




    class  FeatureListViewHolder(val  featureItemBinding : FeatureItemBinding) : RecyclerView.ViewHolder(featureItemBinding.root){
        fun bind(item: FeatureItem, context: Context){
            item.drawable?.let {
                featureItemBinding.ivFeatureIcon.setImageResource(it)
            } ?: kotlin.run {
                Glide
                    .with(context)
                    .load(item.iconUrl?.toUri())
                    .into(featureItemBinding.ivFeatureIcon)
            }
            featureItemBinding.tvFeatureName.text = item.featureName
        }
    }


}