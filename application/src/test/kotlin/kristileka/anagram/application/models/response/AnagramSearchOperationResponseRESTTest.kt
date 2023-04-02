package kristileka.anagram.application.models.response

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnagramSearchOperationResponseRESTTest {

    @Test
    fun `validate field properties AnagramSearchOperationResponseREST`() {
        val storedAnagrams = listOf("test")
        val word = "test"
        val anagramSearchOperationResponseREST = AnagramSearchOperationResponseREST(storedAnagrams, word)
        assertEquals(anagramSearchOperationResponseREST.word, word)
        assertEquals(anagramSearchOperationResponseREST.storedAnagrams, storedAnagrams)
    }
}
