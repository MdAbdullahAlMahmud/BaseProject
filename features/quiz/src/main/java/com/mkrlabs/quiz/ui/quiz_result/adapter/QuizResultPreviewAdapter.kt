package com.mkrlabs.quiz.ui.quiz_result.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mkrlabs.common.core.base.utils.includeDot
import com.mkrlabs.quiz.R
import com.mkrlabs.quiz.data.model.enums.QuestionAnswer
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.databinding.QuestionLayoutBinding

class QuizResultPreviewAdapter :
    RecyclerView.Adapter<QuizResultPreviewAdapter.QuizResultPreviewViewHolder>() {

    private var items = arrayListOf<QuizQuestionItem>()
    fun submitList(list: List<QuizQuestionItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizResultPreviewViewHolder {
        val binding =
            QuestionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizResultPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizResultPreviewViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = items.size


    class QuizResultPreviewViewHolder(val binding: QuestionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: QuizQuestionItem) {
            binding.questionHeader.questionNo.text = item.index_no.toString().includeDot().ifEmpty { "" }
            binding.questionHeader.questionTitle.text = item.question.toString().ifEmpty { "" }
            if (item.explanation?.isNotEmpty() == true){
                binding.questionAnswerExplanationLayout.root.visibility = View.VISIBLE

                val stringBuilder = "Explanation: ${item.explanation}"
                val firstSpaceIndex = stringBuilder.indexOf(" ")
                val spannableStringBuilder = SpannableStringBuilder(stringBuilder)
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(binding.root.context,com.mkrlabs.common.R.color.colorPrimary)), 0, firstSpaceIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                binding.questionAnswerExplanationLayout.explanationDesc.text = spannableStringBuilder
            }
            setOption(item.no_of_choice!!.equals("5"), item)
            setAnswerPreview(item)

        }

        fun setAnswerPreview(item: QuizQuestionItem) {
            if (item.userChoice == null) {
                when(item.answer){
                    QuestionAnswer.Choice_A.toString() -> {
                        binding.questionOption.optionA.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.no_ans_background_style)
                        binding.questionOption.optionA.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionA.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }
                    QuestionAnswer.Choice_B.toString() -> {
                        binding.questionOption.optionB.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.no_ans_background_style)
                        binding.questionOption.optionB.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionB.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_C.toString() -> {
                        binding.questionOption.optionC.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.no_ans_background_style)
                        binding.questionOption.optionC.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionC.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_D.toString() -> {
                        binding.questionOption.optionD.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.no_ans_background_style)
                        binding.questionOption.optionD.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionD.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_E.toString() -> {
                        binding.questionOption.optionE.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.no_ans_background_style)
                        binding.questionOption.optionE.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionE.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }
                }

                Log.v("Quiz","-------------Choice Null-----------------")
            } else if (item.userChoice?.value.equals(item.answer)) {
                when (item.userChoice) {
                    QuestionAnswer.Choice_A -> {
                        binding.questionOption.optionA.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                        binding.questionOption.optionA.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionA.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_B -> {
                        binding.questionOption.optionB.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                        binding.questionOption.optionB.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionB.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_C -> {
                        binding.questionOption.optionC.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                        binding.questionOption.optionC.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionC.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_D -> {
                        binding.questionOption.optionD.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                        binding.questionOption.optionD.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionD.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    QuestionAnswer.Choice_E -> {
                        binding.questionOption.optionE.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                        binding.questionOption.optionE.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionE.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                    }

                    else -> {

                    }
                }
            } else {
                showCorrectAns(item.answer)
                when (item.userChoice) {
                    QuestionAnswer.Choice_A -> {
                        binding.questionOption.optionA.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_wrong)
                        binding.questionOption.optionA.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionA.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_wrong_ans_24)

                    }

                    QuestionAnswer.Choice_B -> {
                        binding.questionOption.optionB.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_wrong)
                        binding.questionOption.optionB.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionB.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_wrong_ans_24)

                    }

                    QuestionAnswer.Choice_C -> {
                        binding.questionOption.optionC.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_wrong)
                        binding.questionOption.optionC.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionC.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_wrong_ans_24)

                    }

                    QuestionAnswer.Choice_D -> {
                        binding.questionOption.optionD.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_wrong)
                        binding.questionOption.optionD.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionD.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_wrong_ans_24)

                    }

                    QuestionAnswer.Choice_E -> {
                        binding.questionOption.optionE.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_wrong)
                        binding.questionOption.optionE.optionStatus.visibility = View.VISIBLE
                        binding.questionOption.optionE.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_wrong_ans_24)

                    }

                    else -> {

                    }
                }
            }
        }

        fun showCorrectAns(answer: String?) {
            when (answer) {
                QuestionAnswer.Choice_A.value -> {
                    binding.questionOption.optionA.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                    binding.questionOption.optionA.optionStatus.visibility = View.VISIBLE
                    binding.questionOption.optionA.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                }

                QuestionAnswer.Choice_B.value -> {
                    binding.questionOption.optionB.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                    binding.questionOption.optionB.optionStatus.visibility = View.VISIBLE
                    binding.questionOption.optionB.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                }

                QuestionAnswer.Choice_C.value -> {
                    binding.questionOption.optionC.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                    binding.questionOption.optionC.optionStatus.visibility = View.VISIBLE
                    binding.questionOption.optionC.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                }

                QuestionAnswer.Choice_D.value -> {
                    binding.questionOption.optionD.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                    binding.questionOption.optionD.optionStatus.visibility = View.VISIBLE
                    binding.questionOption.optionD.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                }

                QuestionAnswer.Choice_E.value -> {
                    binding.questionOption.optionE.optionItemBox.background = ContextCompat.getDrawable(binding.root.context, com.mkrlabs.common.R.drawable.option_selected_right)
                    binding.questionOption.optionE.optionStatus.visibility = View.VISIBLE
                    binding.questionOption.optionE.optionStatus.setImageResource(com.mkrlabs.common.R.drawable.baseline_right_ans_circle_24)

                }

                else -> {

                }
            }
        }

        fun setOption(isEOptionShouldVisible: Boolean, item: QuizQuestionItem) {
            binding.questionOption.optionA.optionTitle.text = "a".includeDot()
            binding.questionOption.optionA.optionDesc.text = item.Choice_A

            binding.questionOption.optionB.optionTitle.text = "b".includeDot()
            binding.questionOption.optionB.optionDesc.text = item.Choice_B

            binding.questionOption.optionC.optionTitle.text = "c".includeDot()
            binding.questionOption.optionC.optionDesc.text = item.Choice_C

            binding.questionOption.optionD.optionTitle.text = "d".includeDot()
            binding.questionOption.optionD.optionDesc.text = item.Choice_D

            if (isEOptionShouldVisible) {
                binding.questionOption.optionE.root.visibility = View.VISIBLE
                binding.questionOption.optionE.optionTitle.text = "e".includeDot()
                binding.questionOption.optionE.optionDesc.text = item.Choice_E
            }
        }
    }


}