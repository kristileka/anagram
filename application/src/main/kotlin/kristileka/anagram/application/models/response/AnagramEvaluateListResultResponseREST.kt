package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramEvaluateListResultResponseREST(
    val word: String,
    val isAnagram: Boolean,
) {
    constructor(evaluationResult: EvaluationResult) : this(
        evaluationResult.word,
        evaluationResult.result,
    )
}
