package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramEvaluateListResultResponseREST(
    var word: String,
    var isAnagram: Boolean
) {
    constructor(evaluationResult: EvaluationResult) : this(
        evaluationResult.word,
        evaluationResult.result
    )
}
