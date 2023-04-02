package kristileka.anagram.domain.extensions

import kristileka.anagram.domain.extensions.Extensions.getPredicate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtensionsTest {

    @Test
    fun `test predicate function works correctly`() {
        val testWord = "dsa"
        val predicateExpected = "ads"

        assertEquals(predicateExpected, testWord.getPredicate())
    }
}
