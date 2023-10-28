package com.mkrlabs.dashboard.ui.dashboard.live_exam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.LiveQuizItem
import com.mkrlabs.dashboard.databinding.LiveQuizItemBinding

class LiveQuizAdapter (private val onItemClicked: (item: LiveQuizItem) -> Unit) :
    RecyclerView.Adapter<LiveQuizAdapter.LiveQuizListViewHolder>(){

    private  val items = arrayListOf<LiveQuizItem>()

    fun submitList (data : List<LiveQuizItem>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveQuizListViewHolder {
        val binding = LiveQuizItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  LiveQuizListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LiveQuizListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,holder.itemView.context)

        holder.featureItemBinding.root.setOnClickListener{
            onItemClicked(item)
        }

    }




    class  LiveQuizListViewHolder(val  featureItemBinding : LiveQuizItemBinding) : RecyclerView.ViewHolder(featureItemBinding.root){
        fun bind(item: LiveQuizItem,context: Context){
            featureItemBinding.quizNameTitleTV.text = item.title
            if (item.isResultPublished == true){
                featureItemBinding.examDinBtn.isEnabled = false
                featureItemBinding.examDinBtn.alpha = 0.5f
                featureItemBinding.quizBottomSection.folafolTv.setTextColor(ContextCompat.getColor(context, com.mkrlabs.common.R.color.text_default))
            }
        }
    }


}