package kristileka.anagram.application.models.request

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramEvaluateListRequestRESTTest {

    @Test
    fun `validate field properties AnagramEvaluateListRequestREST`() {
        val words = listOf<String>()
        val anagramEvaluateListRequestREST = AnagramEvaluateListRequestREST(words)
        assertEquals(words, anagramEvaluateListRequestREST.words)
    }
}
