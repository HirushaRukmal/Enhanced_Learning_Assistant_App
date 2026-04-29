package com.example.enhancedlearningassistantapp.llm

class FakeLlmService {

    fun generateHint(question: String): String {
        return "Hint: Focus on the key concept mentioned in the question and eliminate clearly unrelated options."
    }

    fun explainAnswer(question: String, correctAnswer: String, selectedAnswer: String): String {
        return if (correctAnswer == selectedAnswer) {
            "Explanation: Your answer is correct because it matches the concept tested in the question."
        } else {
            "Explanation: '$selectedAnswer' is not correct here. The correct answer is '$correctAnswer' because it directly fits the concept being assessed."
        }
    }

    fun createFlashcards(topic: String): String {
        return """
            Flashcard 1: Define $topic in one sentence.
            Flashcard 2: List one real-world use of $topic.
            Flashcard 3: Name one mistake students commonly make about $topic.
        """.trimIndent()
    }

    fun lessonSummary(topic: String): String {
        return "Summary: $topic involves understanding the main idea, its practical application, and how to distinguish it from similar concepts."
    }
}