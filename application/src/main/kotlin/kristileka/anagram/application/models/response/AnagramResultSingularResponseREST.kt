package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramResultSingularResponseREST(
    var word: String,
    var result: Boolean
) {
    constructor(evaluationResult: EvaluationResult) : this(
        evaluationResult.word,
        evaluationResult.result
    )
}
