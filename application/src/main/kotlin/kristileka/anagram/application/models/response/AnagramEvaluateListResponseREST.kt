package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramEvaluateListResponseREST(
    var results: List<AnagramResultSingularResponseREST>, var selectedWord: String
) {
    constructor(
        evaluationResults: Pair<List<EvaluationResult>, String>
    ) : this(
        evaluationResults.first.map {
            AnagramResultSingularResponseREST(it)
        }.toList(), evaluationResults.second
    )

    constructor(
        evaluationResult: EvaluationResult
    ) : this(
        listOf(AnagramResultSingularResponseREST(evaluationResult)),
        evaluationResult.potentialAnagram
    )
}
