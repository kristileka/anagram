package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramSingleEvaluationResponseREST(
    var firstWord: String,
    var secondWord: String,
    var result: Boolean
) {
    constructor(evaluationResult: EvaluationResult) : this(
        evaluationResult.word,
        evaluationResult.potentialAnagram,
        evaluationResult.result
    )
}
