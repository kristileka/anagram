package kristileka.anagram.application.models.request

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramEvaluateRequestRESTTest {

    @Test
    fun `validate field properties AnagramEvaluateRequestREST`() {
        val word = "test"
        val potentialAnagram = "estt"
        val anagramEvaluateRequestREST = AnagramEvaluateRequestREST(word, potentialAnagram)
        assertEquals(word, anagramEvaluateRequestREST.firstWord)
        assertEquals(potentialAnagram, anagramEvaluateRequestREST.secondWord)
    }
}
