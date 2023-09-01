package com.mkrlabs.dashboard.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.databinding.SubTopicItemBinding

class QuizListAdapter (private val onItemClicked: (item: QuizResponseItem) -> Unit) : RecyclerView.Adapter<QuizListAdapter.QuizListViewHolder>(){

    private  val items = arrayListOf<QuizResponseItem>()

    fun submitList (data : List<QuizResponseItem>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizListViewHolder {
        val binding = SubTopicItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  QuizListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuizListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,holder.itemView.context)

        holder.subTopicItemBinding.topicRoot.setOnClickListener{
            onItemClicked(item)
        }

    }




    class  QuizListViewHolder(val  subTopicItemBinding: SubTopicItemBinding) : RecyclerView.ViewHolder(subTopicItemBinding.root){
        fun bind(item: QuizResponseItem, context: Context){
            subTopicItemBinding.quizViewCV.visibility = View.VISIBLE
            subTopicItemBinding.tvTopicName.text = item.quiz_title
        }
    }


}