package com.mkrlabs.dashboard.ui.dashboard.live_exam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.dashboard.R
import com.mkrlabs.dashboard.data.model.response.LiveQuizItem
import com.mkrlabs.dashboard.databinding.LiveQuizItemBinding
import com.mkrlabs.dashboard.utils.DateFormatter

class LiveQuizAdapter (
    private val onItemClicked: (item: LiveQuizResponseItem) -> Unit ,
    private val medhaTalika : (item : LiveQuizResponseItem) -> Unit,
    private val syllabusClickListener : (item : LiveQuizResponseItem) -> Unit,
    private val folafolClick : (item : LiveQuizResponseItem , isPublished : Boolean) -> Unit,
    ) :
    RecyclerView.Adapter<LiveQuizAdapter.LiveQuizListViewHolder>(){

    private  val items = arrayListOf<LiveQuizResponseItem>()

    fun submitList (data : List<LiveQuizResponseItem>){
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

        holder.featureItemBinding.examDinBtn.setOnClickListener{
            onItemClicked(item)
        }

        holder.featureItemBinding.quizBottomSection.medhaTalikaTv.setOnClickListener {
            medhaTalika(item)
        }

        holder.featureItemBinding.quizBottomSection.folafolTv.setOnClickListener {
            folafolClick(item,true)
        }



        holder.featureItemBinding.headerSection.quizSyllbus.setOnClickListener {
            syllabusClickListener(item)
        }

    }




    class  LiveQuizListViewHolder(val  featureItemBinding : LiveQuizItemBinding) : RecyclerView.ViewHolder(featureItemBinding.root){
        fun bind(item: LiveQuizResponseItem,context: Context){
            featureItemBinding.quizNameTitleTV.text = item.quiz_title
            featureItemBinding.noOfQuestions.text = "Questions ${item.no_of_question}"
            featureItemBinding.quizTime.text = "Time : ${item.quiz_time } min"
            featureItemBinding.headerSection.quizDate.text = "Date : ${item.exam_date }"
            featureItemBinding.headerSection.quizDay.text = DateFormatter.getDayNameFromDate(item.exam_date.toString())

            if (DateFormatter.examDateAndTodaysDateIsSame(item.exam_date.toString())){
                featureItemBinding.examDinBtn.isEnabled = true
            } else if(DateFormatter.resultPublishedDateAndTodaysDateIsSame(item.result_published_date?:"")){
                featureItemBinding.examDinBtn.isEnabled = false
                featureItemBinding.quizBottomSection.folafolTv.isEnabled = true
                featureItemBinding.quizBottomSection.medhaTalikaTv.isEnabled = true
                featureItemBinding.examDinBtn.alpha = 0.5f
                featureItemBinding.quizBottomSection.folafolTv.setTextColor(ContextCompat.getColor(context, com.mkrlabs.common.R.color.text_default))
                featureItemBinding.quizBottomSection.medhaTalikaTv.setTextColor(ContextCompat.getColor(context, com.mkrlabs.common.R.color.text_default))
            }
            else{
                featureItemBinding.examDinBtn.alpha = 0.5f
                featureItemBinding.examDinBtn.isEnabled = false
                featureItemBinding.quizBottomSection.folafolTv.isEnabled = false
                featureItemBinding.quizBottomSection.medhaTalikaTv.isEnabled = false
            }
        }
    }


}