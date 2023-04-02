package kristileka.anagram.domain.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EvaluationResultTest {

    @Test
    fun `validate field properties EvaluationResult`() {
        val word = "test"
        val potentialAnagram = "estt"
        val result = true
        val evaluationResult = EvaluationResult(word, potentialAnagram, result)
        assertEquals(word, evaluationResult.word)
        assertEquals(potentialAnagram, evaluationResult.potentialAnagram)
        assertEquals(result, evaluationResult.result)
    }
}
