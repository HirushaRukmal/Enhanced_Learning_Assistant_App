package com.example.enhancedlearningassistantapp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enhancedlearningassistantapp.databinding.ItemQuestionBinding
import com.example.enhancedlearningassistantapp.model.Question

class QuestionAdapter(
    private val questions: List<Question>
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var isSubmitted: Boolean = false

    inner class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.textQuestion.text = question.questionText
            binding.radioOption1.text = question.options[0]
            binding.radioOption2.text = question.options[1]
            binding.radioOption3.text = question.options[2]
            binding.radioOption4.text = question.options[3]

            binding.radioGroup.setOnCheckedChangeListener(null)

            when (question.selectedIndex) {
                0 -> binding.radioOption1.isChecked = true
                1 -> binding.radioOption2.isChecked = true
                2 -> binding.radioOption3.isChecked = true
                3 -> binding.radioOption4.isChecked = true
                else -> binding.radioGroup.clearCheck()
            }

            resetOptionColors()

            if (isSubmitted) {
                highlightAnswers(question)
                setOptionsEnabled(false)
            } else {
                setOptionsEnabled(true)
            }

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                if (!isSubmitted) {
                    question.selectedIndex = when (checkedId) {
                        binding.radioOption1.id -> 0
                        binding.radioOption2.id -> 1
                        binding.radioOption3.id -> 2
                        binding.radioOption4.id -> 3
                        else -> -1
                    }
                }
            }
        }

        private fun resetOptionColors() {
            val white = Color.WHITE
            binding.radioOption1.setBackgroundColor(white)
            binding.radioOption2.setBackgroundColor(white)
            binding.radioOption3.setBackgroundColor(white)
            binding.radioOption4.setBackgroundColor(white)
        }

        private fun highlightAnswers(question: Question) {
            val green = Color.parseColor("#A5D6A7")
            val red = Color.parseColor("#EF9A9A")

            val options = listOf(
                binding.radioOption1,
                binding.radioOption2,
                binding.radioOption3,
                binding.radioOption4
            )

            options[question.correctAnswerIndex].setBackgroundColor(green)

            if (question.selectedIndex != -1 && question.selectedIndex != question.correctAnswerIndex) {
                options[question.selectedIndex].setBackgroundColor(red)
            }
        }

        private fun setOptionsEnabled(enabled: Boolean) {
            binding.radioOption1.isEnabled = enabled
            binding.radioOption2.isEnabled = enabled
            binding.radioOption3.isEnabled = enabled
            binding.radioOption4.isEnabled = enabled
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = ItemQuestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size
}