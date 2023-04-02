package kristileka.anagram.application.models.response

import kristileka.anagram.domain.dto.EvaluationResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramEvaluateListResultResponseRESTTest {

    @Test
    fun `validate field properties AnagramEvaluateListResultResponseREST`() {
        val evaluationResult = EvaluationResult("test", "test", false)
        val anagramEvaluateListResultResponseREST = AnagramEvaluateListResultResponseREST(evaluationResult)
        assertEquals(evaluationResult.word, anagramEvaluateListResultResponseREST.word)
        assertEquals(evaluationResult.result, anagramEvaluateListResultResponseREST.isAnagram)
    }
}
