package com.mkrlabs.quiz.ui.quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.common.core.base.utils.includeDot
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.databinding.QuestionLayoutBinding

class QuizQuestionAdapter(private val selectedOption: (item: QuizQuestionItem) -> Unit) :
    RecyclerView.Adapter<QuizQuestionAdapter.QuizQuestionViewHolder>() {

    private var items = arrayListOf<QuizQuestionItem>()

    fun submitList(list: List<QuizQuestionItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizQuestionViewHolder {
        val binding =
            QuestionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizQuestionViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuizQuestionViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class QuizQuestionViewHolder(val binding: QuestionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuizQuestionItem) {
            binding.questionHeader.questionNo.text = item.index_no.toString().includeDot().ifEmpty { "" }
            binding.questionHeader.questionTitle.text = item.question.toString().ifEmpty { "" }
            setOption(item.no_of_choice!!.equals("5") , item)
        }
        fun setOption(isEOptionShouldVisible: Boolean, item: QuizQuestionItem){
            binding.questionOption.optionA.optionTitle.text = "a".includeDot()
            binding.questionOption.optionA.optionDesc.text = item.Choice_A

            binding.questionOption.optionB.optionTitle.text = "b".includeDot()
            binding.questionOption.optionB.optionDesc.text = item.Choice_B

            binding.questionOption.optionC.optionTitle.text = "c".includeDot()
            binding.questionOption.optionC.optionDesc.text = item.Choice_C

            binding.questionOption.optionD.optionTitle.text = "d".includeDot()
            binding.questionOption.optionD.optionDesc.text = item.Choice_D

            if (isEOptionShouldVisible){
                binding.questionOption.optionE.root.visibility = View.VISIBLE
                binding.questionOption.optionE.optionTitle.text = "e".includeDot()
                binding.questionOption.optionE.optionDesc.text = item.Choice_E
            }
        }
    }
}