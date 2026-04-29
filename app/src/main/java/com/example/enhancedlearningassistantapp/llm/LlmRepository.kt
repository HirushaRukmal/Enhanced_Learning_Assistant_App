package com.example.enhancedlearningassistantapp.llm

class LlmRepository(
    private val service: FakeLlmService = FakeLlmService()
) {
    fun getHint(question: String): Pair<String, String> {
        val prompt = "Generate a learning hint for this question: $question"
        val response = service.generateHint(question)
        return prompt to response
    }

    fun getExplanation(question: String, correct: String, selected: String): Pair<String, String> {
        val prompt = "Explain why '$correct' is correct and why '$selected' may be incorrect for: $question"
        val response = service.explainAnswer(question, correct, selected)
        return prompt to response
    }

    fun getFlashcards(topic: String): Pair<String, String> {
        val prompt = "Create 3 flashcards for the topic: $topic"
        val response = service.createFlashcards(topic)
        return prompt to response
    }

    fun getSummary(topic: String): Pair<String, String> {
        val prompt = "Produce a short summary of the lesson topic: $topic"
        val response = service.lessonSummary(topic)
        return prompt to response
    }
}