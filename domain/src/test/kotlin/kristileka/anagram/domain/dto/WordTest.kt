package kristileka.anagram.domain.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WordTest {
    @Test
    fun `validate field properties Word`() {
        val value = "test"
        val predicate = "estt"
        val word = Word(value, predicate)
        assertEquals(value, word.value)
        assertEquals(predicate, word.predicate)
    }
}
