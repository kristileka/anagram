package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramEvaluateListResponseRESTTest {

    @Test
    fun `validate field properties from evaluation results AnagramEvaluateListResponseREST`() {
        val evaluationResult = EvaluationResult("test", "test", false)
        val anagramEvaluateListResponseREST = AnagramEvaluateListResponseREST(evaluationResult)
        assertEquals(evaluationResult.word, anagramEvaluateListResponseREST.results.first().word)
        assertEquals(evaluationResult.result, anagramEvaluateListResponseREST.results.first().isAnagram)
        assertEquals(evaluationResult.potentialAnagram, evaluationResult.potentialAnagram)
    }
}
