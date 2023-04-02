package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult

data class AnagramEvaluateListResponseREST(
    val results: List<AnagramEvaluateListResultResponseREST>,
    val selectedWord: String,
) {
    constructor(
        evaluationResults: Pair<List<EvaluationResult>, String>,
    ) : this(
        evaluationResults.first.map {
            AnagramEvaluateListResultResponseREST(it)
        }.toList(),
        evaluationResults.second,
    )

    constructor(
        evaluationResult: EvaluationResult,
    ) : this(
        listOf(AnagramEvaluateListResultResponseREST(evaluationResult)),
        evaluationResult.potentialAnagram,
    )
}
