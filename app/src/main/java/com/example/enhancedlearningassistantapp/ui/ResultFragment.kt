package com.example.enhancedlearningassistantapp.ui

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.LearningViewModel
import com.example.enhancedlearningassistantapp.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<LearningViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentResultBinding.bind(view)

        val score = arguments?.getInt("score", 0) ?: 0
        val total = arguments?.getInt("total", 0) ?: 0
        val percentage = arguments?.getInt("percentage", 0) ?: 0

        binding.textScoreValue.text = "$score / $total"
        binding.textPercentageValue.text = "$percentage%"

        viewModel.task.observe(viewLifecycleOwner) { task ->
            val builder = SpannableStringBuilder()

            task.questions.forEachIndexed { index, q ->
                val selectedAnswer = if (q.selectedIndex >= 0) q.options[q.selectedIndex] else "Not answered"
                val correctAnswer = q.options[q.correctAnswerIndex]
                val isCorrect = q.selectedIndex == q.correctAnswerIndex
                val status = if (isCorrect) "Correct" else "Wrong"

                builder.append("Question ${index + 1}: ${q.questionText}\n")
                builder.append("Selected Answer: $selectedAnswer\n")
                builder.append("Correct Answer: $correctAnswer\n")

                val start = builder.length
                builder.append("Result: $status\n\n")
                val color = if (isCorrect) Color.parseColor("#2E7D32") else Color.parseColor("#C62828")
                builder.setSpan(
                    ForegroundColorSpan(color),
                    start,
                    builder.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            binding.textResults.text = builder
        }

        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
