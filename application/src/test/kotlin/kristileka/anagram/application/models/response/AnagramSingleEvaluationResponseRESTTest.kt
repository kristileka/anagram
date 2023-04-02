package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramSingleEvaluationResponseRESTTest {

    @Test
    fun `validate field properties AnagramSingleEvaluationResponseREST`() {
        val evaluationResult = EvaluationResult("test", "test", false)
        val anagramSingleEvaluationResponseREST = AnagramSingleEvaluationResponseREST(evaluationResult)
        assertEquals(evaluationResult.word, anagramSingleEvaluationResponseREST.firstWord)
        assertEquals(evaluationResult.potentialAnagram, anagramSingleEvaluationResponseREST.secondWord)
        assertEquals(evaluationResult.result, anagramSingleEvaluationResponseREST.result)
    }
}
