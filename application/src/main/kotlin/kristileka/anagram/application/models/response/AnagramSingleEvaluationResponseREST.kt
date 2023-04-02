package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramSingleEvaluationResponseREST(
    val firstWord: String,
    val secondWord: String,
    val result: Boolean,
) {
    constructor(evaluationResult: EvaluationResult) : this(
        evaluationResult.word,
        evaluationResult.potentialAnagram,
        evaluationResult.result,
    )
}
