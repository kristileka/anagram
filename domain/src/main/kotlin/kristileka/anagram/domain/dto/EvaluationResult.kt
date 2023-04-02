package kristileka.anagram.domain.dto

data class EvaluationResult(
    val word: String,
    val potentialAnagram: String,
    val result: Boolean,
)
